package com.big.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.big.entity.BigFile;
import com.big.mapper.BigFileMapper;
import com.big.service.BigFileService;
import com.big.utils.ResponseResult;
import com.big.vo.MergeChunkVo;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件管理(BigFile)表服务实现类
 *
 * @author makejava
 * @since 2024-01-10 20:16:36
 */
@Slf4j
@Service("bigFileService")
public class BigFileServiceImpl implements BigFileService {

    private static final String BUCKET_NAME = "big-event";

    @Autowired
    private BigFileMapper fileMapper;

    @Autowired
    private BigFileService fileService;

    @Autowired
    private MinioClient minioClient;

    /**
     * 文件上传前检查
     */
    @Override
    public ResponseResult checkFile(String fileMd5) {
        //查询数据库是否存在文件
        BigFile file = fileMapper.selectFileByMd5(fileMd5);

        if (file != null) {
            String fileLocation = file.getFileLocation();
            boolean flag = checkFileIsExist(BUCKET_NAME, fileLocation);
            if (flag) {
                return ResponseResult.okResult(1000, "文件已存在");
            }
            return ResponseResult.okResult(2000, "文件不存在");
        }
        return ResponseResult.okResult(2000, "文件不存在");
    }

    /**
     * 上传分块前检查分块
     */
    @Override
    public ResponseResult checkChunk(String fileMd5, int chunkIndex) {
        String fileFolderPath = getChunkFileFolderPath(fileMd5);

        boolean flag = checkFileIsExist(BUCKET_NAME, fileFolderPath + chunkIndex);
        if (flag) {
            return ResponseResult.okResult(1000, "分块已存在");
        }
        return ResponseResult.okResult(2000, "分块不存在");
    }

    /**
     * 判断文件是否存在
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
     * @return true存在, 反之
     */
    public Boolean checkFileIsExist(String bucketName, String objectName) {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucketName).object(objectName).build()
            );
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 上传分块
     */
    @Override
    public ResponseResult uploadChunk(String fileMd5, int chunkIndex, String localChunkFilePath) {
        //得到分块文件的目录路径
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        //得到分块文件的路径
        String chunkFilePath = chunkFileFolderPath + chunkIndex;

        String mimType = getMimType("");
        //将文件存储至minIO
        boolean result = addMediaFilesToMinIo(localChunkFilePath, mimType, BUCKET_NAME, chunkFilePath);
        if (!result) {
            return ResponseResult.errorResult("上传分块失败");
        }
        return ResponseResult.okResult();

    }

    /**
     * 合并分块
     */
    @Override
    public ResponseResult mergechunks(MergeChunkVo mergeChunkVo) {
        String fileMd5 = mergeChunkVo.getFileMd5();
        String fileName = mergeChunkVo.getFileName();
        int chunkTotal = mergeChunkVo.getChunkTotal();
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        //找到分块文件调用minio的sdk合并文件
        ArrayList<ComposeSource> sources = new ArrayList<>();
        for (int i = 0; i < chunkTotal; i++) {
            ComposeSource composeSource = ComposeSource.builder()
                    .bucket(BUCKET_NAME)
                    .object(chunkFileFolderPath + i)//分块文件
                    .build();
            sources.add(composeSource);
        }
        //扩展名
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //合并后的objectName
        String objectName = getFilePathByMd5(fileMd5, extension);
        //指定合并后的objectName信息
        ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object(objectName)
                .sources(sources)//指定源文件
                .build();
        try {
            minioClient.composeObject(composeObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("合并文件出错，bucket：{}，objectName：{}，错误信息：{}", BUCKET_NAME, objectName, e.getMessage());
            return ResponseResult.errorResult("合并文件出错");
        }
        //校验合并后的文件与原文件是否一致
        //先下载文件
        File file = downloadFileFromMinIO("big-event", objectName);
        //文件类型
        String type = FileTypeUtil.getType(file);
        //计算合并后的MD5
        try (FileInputStream fileInputStream = new FileInputStream(file);) {
            String mergeFileMd5 = DigestUtils.md5Hex(fileInputStream);
            //比较原始MD5
            if (!fileMd5.equals(mergeFileMd5)) {
                return ResponseResult.errorResult("文件校验失败");
            }
        } catch (Exception e) {
            return ResponseResult.errorResult("文件校验失败");
        }

        //将文件信息入库(非事务方法调用事务方法，要用代理对象调，注入自己的service，再调用)
        BigFile mediaFiles = fileService.addMediaFilesToDb(fileMd5, fileName, type, objectName, new Date(), BUCKET_NAME, file.length());
        if (mediaFiles == null) {
            return ResponseResult.errorResult("文件入库失败");
        }
        //清理分块文件
        clearChunkFiles(chunkFileFolderPath, chunkTotal);
        return ResponseResult.okResult();
    }

    /**
     * 清除分块文件
     *
     * @param chunkFileFolderPath 分块文件路径
     * @param chunkTotal          分块文件总数
     */
    private void clearChunkFiles(String chunkFileFolderPath, int chunkTotal) {

        try {
            List<DeleteObject> deleteObjects = Stream.iterate(0, i -> ++i)
                    .limit(chunkTotal)
                    .map(i -> new DeleteObject(chunkFileFolderPath.concat(Integer.toString(i))))
                    .collect(Collectors.toList());

            RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder().bucket(BUCKET_NAME).objects(deleteObjects).build();
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(removeObjectsArgs);
            for (Result<DeleteError> result : results) {
                DeleteError deleteError = result.get();
                log.error("清楚分块文件失败,objectname:{}", deleteError.objectName(), deleteError.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("清楚分块文件失败,chunkFileFolderPath:{}", chunkFileFolderPath, e);
        }
    }

    /**
     * 从minio下载文件
     *
     * @param bucket     桶
     * @param objectName 对象名称
     * @return 下载后的文件
     */
    public File downloadFileFromMinIO(String bucket, String objectName) {
        //临时文件
        File minioFile = null;
        FileOutputStream outputStream = null;
        try {
            InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build());
            //创建临时文件
            minioFile = File.createTempFile("minio", ".merge");
            outputStream = new FileOutputStream(minioFile);
            IOUtils.copy(stream, outputStream);
            return minioFile;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 得到合并后的文件的地址
     *
     * @param fileMd5 文件id即md5值
     * @param fileExt 文件扩展名
     * @return
     */
    private String getFilePathByMd5(String fileMd5, String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
    }

    /**
     * 将文件上传至minio
     */
    private boolean addMediaFilesToMinIo(String localFilePath, String mimType, String bucket, String objectName) {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)//添加子目录
                    .filename(localFilePath)
                    .contentType(mimType)//默认根据扩展名确定文件内容类型，也可以指定
                    .build();
            //上传
            minioClient.uploadObject(uploadObjectArgs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败");
        }
        return false;
    }

    /**
     * 扩展名取出mimeType
     */
    private static String getMimType(String extension) {
        //根据扩展名取出mimeType
        if (extension == null) {
            extension = "";
        }
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        String mimType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (extensionMatch != null) {
            mimType = extensionMatch.getMimeType();
        }
        return mimType;
    }

    /**
     * 得到分块文件的目录
     */
    private String getChunkFileFolderPath(String fileMd5) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + "chunk" + "/";
    }

    /**
     * 将文件信息入库
     */
    @Override
    public BigFile addMediaFilesToDb(String fileMd5, String fileName, String category, String filePath, Date date, String bucket, long size) {
        BigFile file = new BigFile();
        file.setFileMd5(fileMd5);
        file.setFileName(fileName);
        file.setFileCategory(category);
        file.setFileLocation(filePath);
        file.setTime(date);
        file.setBucket(bucket);
        file.setSize(size);

        fileMapper.addMediaFiles(file);

        if (file.getId() != null) {
            return file;
        }
        return null;
    }
}


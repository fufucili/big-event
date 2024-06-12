package com.big.service;

import com.big.entity.BigFile;
import com.big.utils.ResponseResult;
import com.big.vo.MergeChunkVo;

import java.util.Date;

/**
 * 文件管理(BigFile)表服务接口
 *
 * @author makejava
 * @since 2024-01-10 20:16:36
 */
public interface BigFileService {

    /**
     * 文件上传前检查文件
     */
    ResponseResult checkFile(String fileMd5);

    /**
     * 分块上传前检查
     */
    ResponseResult checkChunk(String fileMd5, int chunkIndex);

    /**
     * 上传分块
     */
    ResponseResult uploadChunk(String fileMd5, int chunkIndex, String absolutePath);

    /**
     * 合并分块
     */
    ResponseResult mergechunks(MergeChunkVo mergeChunkVo);

    /**
     * 将文件信息入库
     *
     * @param fileMd5: 文件的md5值
     * @param fileName: 文件名称
     * @param category: 文件类型
     * @param filePath: 文件路径
     * @param date: 时间
     * @param bucket: 桶
     * @param size: 大小
     * @return BigFile
     */
    BigFile addMediaFilesToDb(String fileMd5, String fileName, String category, String filePath, Date date, String bucket, long size);
}


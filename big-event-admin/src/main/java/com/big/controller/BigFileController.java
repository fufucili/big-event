package com.big.controller;

import com.big.service.BigFileService;
import com.big.utils.ResponseResult;
import com.big.vo.MergeChunkVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 文件管理(BigFile)表控制层
 *
 * 上传
 *
 * @author makejava
 * @since 2024-01-10 20:16:47
 */
@RestController
@RequestMapping("/bigFile")
public class BigFileController {
    /**
     * 服务对象
     */
    @Resource
    private BigFileService fileService;

    @ApiOperation(value = "文件上传前检查文件")
    @GetMapping("/upload/checkfile")
    public ResponseResult checkfile(@RequestParam("fileMd5") String fileMd5) {
        return fileService.checkFile(fileMd5);
    }

    @ApiOperation(value = "分块文件上传前的检测")
    @GetMapping("/upload/checkchunk")
    public ResponseResult checkchunk(@RequestParam("fileMd5") String fileMd5,
                              @RequestParam("chunkIndex") int chunkIndex) {
        return fileService.checkChunk(fileMd5, chunkIndex);
    }

    @ApiOperation(value = "上传分块文件")
    @PostMapping("/upload/uploadchunk")
    public ResponseResult uploadchunk(@RequestParam("file") MultipartFile file,
                                      @RequestParam("fileMd5") String fileMd5,
                                      @RequestParam("chunkIndex") int chunkIndex) {
        //创建临时文件
        File tempFile = null;
        try {
            tempFile = File.createTempFile("minio", "temp");
            //上传的文件拷贝到临时文件
            file.transferTo(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //文件路径
        String absolutePath = tempFile.getAbsolutePath();

        return fileService.uploadChunk(fileMd5, chunkIndex, absolutePath);
    }

    @ApiOperation(value = "合并文件")
    @PostMapping("/upload/mergechunks")
    public ResponseResult mergechunks(@RequestBody MergeChunkVo mergeChunkVo) {
        return fileService.mergechunks(mergeChunkVo);
    }
}


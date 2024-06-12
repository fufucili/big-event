package com.big.controller;

import com.big.service.IFileService;
import com.big.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件管理
 *
 * @author TuYongbin
 * @Date 2024/1/12 13:08
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    /**
     * 查询文件列表
     */
    @GetMapping("/list")
    public ResponseResult getFileList(){
        return fileService.getFileList();
    }
}

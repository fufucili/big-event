package com.big.service.impl;

import com.big.entity.BigFile;
import com.big.mapper.FileMapper;
import com.big.service.IFileService;
import com.big.utils.ResponseResult;
import com.big.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现层
 *
 * @author TuYongbin
 * @Date 2024/1/12 13:11
 */
@Service
public class FileServiceImpl implements IFileService {

    private static final String FILE_PREFIX = "http://192.168.101.133:9090/";

    @Autowired
    private FileMapper fileMapper;

    /**
     * 文件列表
     */
    @Override
    public ResponseResult getFileList() {
        List<BigFile> files = fileMapper.selectFileList();
        List<FileVo> fileVoList = files.stream().map(file -> {
            FileVo fileVo = new FileVo();
            fileVo.setFileName(file.getFileName());
            fileVo.setFileCategory(file.getFileCategory());
            fileVo.setId(file.getId());
            fileVo.setFileTime(file.getTime());
            fileVo.setFileSize(file.getSize().intValue() / 1024 + "KB");
            fileVo.setFilePreview(FILE_PREFIX + file.getBucket() + "/" + file.getFileLocation());
            return fileVo;
        }).collect(Collectors.toList());
        return ResponseResult.okResult(fileVoList);
    }
}

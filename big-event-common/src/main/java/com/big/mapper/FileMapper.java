package com.big.mapper;

import com.big.entity.BigFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件管理db
 *
 * @author TuYongbin
 * @Date 2024/1/12 13:12
 */
@Mapper
public interface FileMapper {
    List<BigFile> selectFileList();
}

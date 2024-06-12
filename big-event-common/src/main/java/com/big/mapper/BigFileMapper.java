package com.big.mapper;

import com.big.entity.BigFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 文件管理(BigFile)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-10 20:16:36
 */
@Mapper
public interface BigFileMapper {

    BigFile selectFileByMd5(@Param("fileMd5") String fileMd5);

    void addMediaFiles(@Param("file") BigFile file);
}


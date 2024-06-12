package com.big.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 文件管理(BigFile)表实体类
 *
 * @author makejava
 * @since 2024-01-10 20:16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_file")
public class BigFile {
    //id@TableId
    private Long id;

    //文件md5
    private String fileMd5;
    //文件名称
    private String fileName;
    //文件类别【0：视频；1：图片】
    private String fileCategory;
    //文件路径
    private String fileLocation;
    //时间
    private Date time;
    //桶
    private String bucket;
    //文件大小
    private Long size;
    //是否删除
    private String isDelete;
}


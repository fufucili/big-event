package com.big.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 文章信息(Articles)表实体类
 *
 * @author makejava
 * @since 2023-11-21 16:55:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_articles")
public class Articles {
    @TableId
    private Integer id;

    private String title;
    
    private String content;
    
    private String coverImg;
    
    private String pubDate;
    
    private String state;
    
    private Integer isDelete;
    
    private Integer cateId;
    
    private Integer authorId;

}


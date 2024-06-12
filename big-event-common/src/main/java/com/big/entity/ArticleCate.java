package com.big.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 文章分类信息(ArticleCate)表实体类
 *
 * @author makejava
 * @since 2023-11-21 16:55:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_article_cate")
public class ArticleCate {
    @TableId
    private Long id;

    private String name;
    
    private String alias;

    //文章分类是否是被删除 0 表示 未被删除 1 表示 已被删除
    private Integer isDelete;
}


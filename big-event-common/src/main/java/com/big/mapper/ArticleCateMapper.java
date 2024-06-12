package com.big.mapper;

import com.big.entity.ArticleCate;
import com.big.utils.ResponseResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章分类mapper
 *
 * @author TuYongbin
 * @Date 2023/11/21 17:00
 */
@Mapper
public interface ArticleCateMapper {
    List<ArticleCate> getChannelList();

    Integer insertArticleCate(ArticleCate articleCate);

    Integer updateArticleCate(ArticleCate articleCate);

    Integer deleterChannelCate(Long id);
}

package com.big.service;

import com.big.entity.ArticleCate;
import com.big.utils.ResponseResult;

import java.util.List;

/**
 * 文章分类service
 *
 * @author TuYongbin
 * @Date 2023/11/21 17:04
 */
public interface IArticleCateService {
    /**
     * 查询分类列表
     */
    List<ArticleCate> getChannelList();

    /**
     * 新增分类
     */
    ResponseResult addChannelCate(ArticleCate articleCate);

    /**
     * 修改分类
     */
    ResponseResult editChannelCate(ArticleCate articleCate);

    /**
     * 删除分类
     */
    ResponseResult deleterChannelCate(Long id);
}

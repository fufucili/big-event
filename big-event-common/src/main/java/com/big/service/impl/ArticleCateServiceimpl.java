package com.big.service.impl;

import com.big.entity.ArticleCate;
import com.big.mapper.ArticleCateMapper;
import com.big.service.IArticleCateService;
import com.big.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类service实现
 *
 * @author TuYongbin
 * @Date 2023/11/21 17:04
 */
@Service
public class ArticleCateServiceimpl implements IArticleCateService {
    @Autowired
    private ArticleCateMapper articleCateMapper;

    /**
     * 查询文章分类
     */
    @Override
    public List<ArticleCate> getChannelList() {
        return articleCateMapper.getChannelList();
    }

    /**
     * 新增分类
     */
    @Override
    public ResponseResult addChannelCate(ArticleCate articleCate) {
        Integer integer = articleCateMapper.insertArticleCate(articleCate);
        if(integer >= 1){
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult("插入失败");
        }
    }

    /**
     * 修改分类
     */
    @Override
    public ResponseResult editChannelCate(ArticleCate articleCate) {
        Integer integer = articleCateMapper.updateArticleCate(articleCate);
        if(integer >= 1){
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult("修改失败");
        }
    }

    /**
     * 删除分类
     */
    @Override
    public ResponseResult deleterChannelCate(Long id) {
        Integer integer = articleCateMapper.deleterChannelCate(id);
        if(integer >= 1){
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult("删除失败");
        }
    }
}

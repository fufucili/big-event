package com.big.controller;

import com.big.entity.ArticleCate;
import com.big.service.IArticleCateService;
import com.big.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.spi.ResolveResult;
import java.util.List;


/**
 * 文章分类信息(ArticleCate)表控制层
 *
 * @author makejava
 * @since 2023-11-21 16:51:40
 */
@RestController
@RequestMapping("/articleCate")
public class ArticleCateController {
    /**
     * 服务对象
     */
    @Resource
    private IArticleCateService articleCateService;

    /**
     * 获取分类集合
     */
    @GetMapping("/list")
    public ResponseResult getChannelList() {
        List<ArticleCate> list = articleCateService.getChannelList();
        return ResponseResult.okResult(list);
    }

    /**
     * 新增分类
     */
    @PostMapping("/add")
    public ResponseResult addChannelCate(@RequestBody ArticleCate articleCate){
        return articleCateService.addChannelCate(articleCate);
    }

    /**
     * 修改分类
     */
    @PutMapping("/edit")
    public ResponseResult editChannelCate(@RequestBody ArticleCate articleCate){
        return articleCateService.editChannelCate(articleCate);
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/deleter")
    public ResponseResult deleterChannelCate(@RequestParam Long id){
        return articleCateService.deleterChannelCate(id);
    }
}


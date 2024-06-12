package com.big.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.big.entity.Articles;
import com.big.service.IArticlesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 文章信息(Articles)表控制层
 *
 * @author makejava
 * @since 2023-11-21 16:52:36
 */
@RestController
@RequestMapping("articles")
public class ArticlesController {
    /**
     * 服务对象
     */
    @Resource
    private IArticlesService articlesService;
}


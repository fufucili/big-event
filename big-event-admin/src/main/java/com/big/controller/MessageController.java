package com.big.controller;

import com.big.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2024-01-15 10:06:53
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;


}


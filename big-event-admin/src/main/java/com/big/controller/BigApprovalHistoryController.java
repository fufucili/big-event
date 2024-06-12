package com.big.controller;

import com.big.service.IApprovalHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * (ApprovalHistory)表控制层
 *
 * @author makejava
 * @since 2023-12-09 13:40:42
 */
@RestController
@RequestMapping("approvalHistory")
public class BigApprovalHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private IApprovalHistoryService IApprovalHistoryService;

}


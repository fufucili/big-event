package com.big.controller;

import com.big.service.IApprovalProcessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ApprovalProcess)表控制层
 *
 * @author makejava
 * @since 2023-12-09 13:40:49
 */
@RestController
@RequestMapping("approvalProcess")
public class BigApprovalProcessController {
    /**
     * 服务对象
     */
    @Resource
    private IApprovalProcessService IApprovalProcessService;

}


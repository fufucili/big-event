package com.big.controller;

import com.big.service.IApprovalConfigService;
import com.big.utils.ResponseResult;
import com.big.vo.ApprovalConfigVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批分类管理(ApprovalConfig)表控制层
 *
 * @author makejava
 * @since 2023-12-09 13:40:24
 */
@RestController
@RequestMapping("approvalConfig")
public class BigApprovalConfigController {
    /**
     * 服务对象
     */
    @Resource
    private IApprovalConfigService approvalConfigService;

    /**
     * 获取审批配置列表
     */
    @GetMapping("/list")
    public ResponseResult list(){
        List<ApprovalConfigVo> configVoList = approvalConfigService.getApprovalConfigList();
        return ResponseResult.okResult(configVoList);
    }

}


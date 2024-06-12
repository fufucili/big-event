package com.big.controller;

import com.big.entity.ApprovalFlowConfig;
import com.big.service.IApprovalFlowConfigService;
import com.big.utils.ResponseResult;
import com.big.vo.ApprovalConfigVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审核流程配置(ApprovalFlowConfig)表控制层
 *
 * @author makejava
 * @since 2023-12-09 13:40:33
 */
@RestController
@RequestMapping("approvalFlowConfig")
public class BigApprovalFlowConfigController {
    /**
     * 服务对象
     */
    @Resource
    private IApprovalFlowConfigService approvalFlowConfigService;

    /**
     * 通过审核配置id获取审核人
     */
    @GetMapping("/reviewerList")
    public ResponseResult getReviewerList(ApprovalConfigVo approvalConfigVo){
        List<ApprovalFlowConfig> flowConfigs = approvalFlowConfigService.getReviewerList(approvalConfigVo);
        return ResponseResult.okResult(flowConfigs);
    }

    /**
     * 新增审批配置
     */
    @PostMapping("/add")
    public ResponseResult addReviewer(@RequestBody ApprovalConfigVo approvalConfigVo){
        return approvalFlowConfigService.addReviewer(approvalConfigVo);
    }

    /**
     * 修改审批配置
     */
    @PutMapping("/update")
    public ResponseResult updateReviewer(@RequestBody ApprovalConfigVo approvalConfigVo){
        return approvalFlowConfigService.updateReviewer(approvalConfigVo);
    }

    /**
     * 删除流程配置
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteApprovalConfig(@PathVariable("id") String id){
        return approvalFlowConfigService.deleteApprovalConfig(Long.valueOf(id));
    }
}


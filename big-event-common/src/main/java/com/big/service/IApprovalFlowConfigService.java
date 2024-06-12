package com.big.service;

import com.big.entity.ApprovalFlowConfig;
import com.big.utils.ResponseResult;
import com.big.vo.ApprovalConfigVo;

import java.util.List;

/**
 * 审核流程配置(ApprovalFlowConfig)表服务接口
 *
 * @author makejava
 * @since 2023-12-09 13:39:47
 */
public interface IApprovalFlowConfigService {
    /**
     * 通过审核配置id获取审核流程
     */
    List<ApprovalFlowConfig> getReviewerList(ApprovalConfigVo approvalConfigVo);

    /**
     * 新增审核配置
     */
    ResponseResult addReviewer(ApprovalConfigVo approvalConfigVo);

    /**
     * 修改审核配置
     */
    ResponseResult updateReviewer(ApprovalConfigVo approvalConfigVo);

    /**
     * 删除配置
     */
    ResponseResult deleteApprovalConfig(Long id);
}


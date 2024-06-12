package com.big.service.impl;

import com.big.entity.ApprovalConfig;
import com.big.entity.ApprovalFlowConfig;
import com.big.mapper.ApprovalFlowConfigMapper;
import com.big.service.IApprovalFlowConfigService;
import com.big.utils.ResponseResult;
import com.big.vo.ApprovalConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 审核流程配置(ApprovalFlowConfig)表服务实现类
 *
 * @author makejava
 * @since 2023-12-09 13:39:47
 */
@Service("approvalFlowConfigService")
public class ApprovalFlowConfigServiceImpl implements IApprovalFlowConfigService {
    @Autowired
    private ApprovalFlowConfigMapper approvalFlowConfigMapper;

    /**
     * 通过审核配置id获取审核流程
     */
    @Override
    public List<ApprovalFlowConfig> getReviewerList(ApprovalConfigVo approvalConfigVo) {
        return approvalFlowConfigMapper.selectReviewerList(approvalConfigVo);
    }

    /**
     * 新增审核配置
     */
    @Transactional
    @Override
    public ResponseResult addReviewer(ApprovalConfigVo approvalConfigVo) {
        //新增审核配置
        ApprovalConfig approvalConfig = new ApprovalConfig();
        approvalConfig.setApprovalName(approvalConfigVo.getApprovalName());
        approvalConfig.setSubmitUser(approvalConfigVo.getSubmitUser());
        approvalFlowConfigMapper.insertReviewer(approvalConfig);

        Long approvalConfigId = approvalConfig.getId();

        //新增审核流程
        List<ApprovalFlowConfig> approvalFlowList = new ArrayList<>();
        for (int i = 0; i < approvalConfigVo.getReviewerList().size(); i++) {
            ApprovalFlowConfig approvalFlowConfig = new ApprovalFlowConfig();
            approvalFlowConfig.setApprovalConfigId(approvalConfigId);
            approvalFlowConfig.setLevel(i + 1);
            approvalFlowConfig.setApprovalUserId(approvalConfigVo.getReviewerList().get(i));
            approvalFlowList.add(approvalFlowConfig);
        }
        approvalFlowConfigMapper.batchInsertApprovalFlow(approvalFlowList);
        return ResponseResult.okResult();
    }

    /**
     * 修改审核配置
     */
    @Override
    @Transactional
    public ResponseResult updateReviewer(ApprovalConfigVo approvalConfigVo) {
        //更改审批配置
        ApprovalConfig approvalConfig = new ApprovalConfig();
        approvalConfig.setId(approvalConfigVo.getId());
        approvalConfig.setSubmitUser(approvalConfigVo.getSubmitUser());
        approvalConfig.setApprovalName(approvalConfigVo.getApprovalName());
        approvalFlowConfigMapper.updateApprovalConfig(approvalConfig);

        //更改审批流程配置
        approvalFlowConfigMapper.deleteFlowConfigByConfigId(approvalConfigVo.getId());
        List<ApprovalFlowConfig> approvalFlowList = new ArrayList<>();
        for (int i = 0; i < approvalConfigVo.getReviewerList().size(); i++) {
            ApprovalFlowConfig approvalFlowConfig = new ApprovalFlowConfig();
            approvalFlowConfig.setApprovalConfigId(approvalConfigVo.getId());
            approvalFlowConfig.setLevel(i + 1);
            approvalFlowConfig.setApprovalUserId(approvalConfigVo.getReviewerList().get(i));
            approvalFlowList.add(approvalFlowConfig);
        }
        approvalFlowConfigMapper.batchInsertApprovalFlow(approvalFlowList);
        return ResponseResult.okResult();
    }

    /**
     * 删除配置
     */
    @Transactional
    @Override
    public ResponseResult deleteApprovalConfig(Long id) {
        approvalFlowConfigMapper.deleteApprovalConfig(id);
        approvalFlowConfigMapper.deleteFlowConfigByConfigId(id);
        return ResponseResult.okResult();
    }
}


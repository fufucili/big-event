package com.big.service.impl;

import com.big.mapper.ApprovalConfigMapper;
import com.big.service.IApprovalConfigService;
import com.big.vo.ApprovalConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批分类管理(ApprovalConfig)表服务实现类
 *
 * @author makejava
 * @since 2023-12-09 13:39:36
 */
@Service("approvalConfigService")
public class ApprovalConfigServiceImpl implements IApprovalConfigService {

    @Autowired
    private ApprovalConfigMapper approvalConfigMapper;

    /**
     * 获取审批配置列表
     */
    @Override
    public List<ApprovalConfigVo> getApprovalConfigList() {
        return approvalConfigMapper.selectApprovalConfigList();
    }
}


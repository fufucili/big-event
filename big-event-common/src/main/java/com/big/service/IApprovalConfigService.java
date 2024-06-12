package com.big.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.big.entity.ApprovalConfig;
import com.big.vo.ApprovalConfigVo;

import java.util.List;

/**
 * 审批分类管理(ApprovalConfig)表服务接口
 *
 * @author makejava
 * @since 2023-12-09 13:39:36
 */
public interface IApprovalConfigService {

    /**
     * 获取审批配置列表
     */
    List<ApprovalConfigVo> getApprovalConfigList();

}


package com.big.service;

import com.big.dto.AddApprovalDTO;
import com.big.utils.ResponseResult;
import com.big.vo.MyApprovalInfoVo;

import java.util.List;

/**
 * 审批主表(ApprovalInfo)表服务接口
 *
 * @author makejava
 * @since 2024-01-09 09:51:58
 */
public interface ApprovalInfoService {

    /**
     * 新增审批
     */
    ResponseResult addApproval(AddApprovalDTO addApproval);

    /**
     * 查询我的审批
     */
    List<MyApprovalInfoVo> getMyApproval();
}


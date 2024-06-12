package com.big.service;

import com.big.entity.ApprovalRecord;
import com.big.utils.ResponseResult;
import com.big.vo.ApprovalVo;
import com.big.vo.MyApprovalInfoVo;
import com.big.vo.MyHandleVo;

import java.util.List;

/**
 * 审批记录表(ApprovalRecord)表服务接口
 *
 * @author makejava
 * @since 2024-01-09 09:52:39
 */
public interface ApprovalRecordService {

    /**
     * 审批
     */
    ResponseResult approval(ApprovalVo approvalVo);

    /**
     * 我的待审
     */
    List<MyHandleVo> getPending();
}


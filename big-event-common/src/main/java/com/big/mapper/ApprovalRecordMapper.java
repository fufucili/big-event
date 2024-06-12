package com.big.mapper;

import com.big.entity.ApprovalRecord;
import com.big.vo.MyHandleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审批记录表(ApprovalRecord)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-09 09:52:39
 */
@Mapper
public interface ApprovalRecordMapper {

    void insertApprovalRecord(@Param("approvalRecord") ApprovalRecord approvalRecord);

    List<MyHandleVo> selectPending(@Param("userId") Long userId);

    ApprovalRecord getRecordById(@Param("approvalRecordId") Long approvalRecordId);

    void updateRecord(@Param("approvalRecord") ApprovalRecord approvalRecord);
}


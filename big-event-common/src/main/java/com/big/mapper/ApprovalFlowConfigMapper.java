package com.big.mapper;

import com.big.entity.ApprovalConfig;
import com.big.entity.ApprovalFlowConfig;
import com.big.vo.ApprovalConfigVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审核流程配置(ApprovalFlowConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-09 13:39:47
 */
@Mapper
public interface ApprovalFlowConfigMapper {

    List<ApprovalFlowConfig> selectReviewerList(@Param("approvalConfigVo") ApprovalConfigVo approvalConfigVo);

    void insertReviewer(@Param("approvalConfig") ApprovalConfig approvalConfig);

    void batchInsertApprovalFlow(@Param("approvalFlowList") List<ApprovalFlowConfig> approvalFlowList);

    void updateApprovalConfig(@Param("approvalConfig") ApprovalConfig approvalConfig);

    void batchUpdateFlowConfig(@Param("approvalFlowList") List<ApprovalFlowConfig> approvalFlowList);

    void deleteFlowConfigByConfigId(@Param("id") Long id);

    void deleteApprovalConfig(@Param("id") Long id);
}


package com.big.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.big.entity.ApprovalConfig;
import com.big.vo.ApprovalConfigVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审批分类管理(ApprovalConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-09 13:39:36
 */
@Mapper
public interface ApprovalConfigMapper extends BaseMapper<ApprovalConfig> {

    List<ApprovalConfigVo> selectApprovalConfigList();

    List<String> selectApprovalReviewUserList(@Param("approvalConfigId") Long approvalConfigId);
}


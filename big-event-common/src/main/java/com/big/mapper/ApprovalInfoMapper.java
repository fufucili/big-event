package com.big.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.big.entity.ApprovalInfo;
import com.big.vo.MyApprovalInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审批主表(ApprovalInfo)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-09 09:51:58
 */
@Mapper
public interface ApprovalInfoMapper {

    void insertApprovalInfo(@Param("approvalInfo") ApprovalInfo approvalInfo);

    List<MyApprovalInfoVo> selectMyApproval(@Param("userId") Long userId);

    ApprovalInfo getInfoById(@Param("approvalInfoId") Long approvalInfoId);

    void updateInfo(@Param("approvalInfo") ApprovalInfo approvalInfo);
}



package com.big.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (ApprovalHistory)表实体类
 *
 * @author makejava
 * @since 2023-12-09 13:36:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_approval_history")
public class ApprovalHistory {
    //审批历史id@TableId
    private Integer id;

    //流程id
    private Integer approvalProcessId;
    //审核级别
    private Integer approvalLevel;
    //审核结果(0:同意；1：拒绝）
    private Integer approvalStatus;
    //提交人
    private Integer submitUser;
    //审核意见
    private String idea;
    //审核人
    private Integer approvalUser;
    //状态（0：正在审核记录；1：以往记录）
    private Integer status;


}


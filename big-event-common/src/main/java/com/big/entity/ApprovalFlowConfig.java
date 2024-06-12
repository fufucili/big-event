package com.big.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 审核流程配置(ApprovalFlowConfig)表实体类
 *
 * @author makejava
 * @since 2023-12-09 13:36:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_approval_flow_config")
public class ApprovalFlowConfig {
    //id@TableId
    private Long id;

    //审批配置id
    private Long approvalConfigId;
    //审核级别
    private Integer level;
    //审核人id
    private Long approvalUserId;
}


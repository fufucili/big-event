package com.big.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 审批分类管理(ApprovalConfig)表实体类
 *
 * @author makejava
 * @since 2023-12-09 13:36:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_approval_config")
public class ApprovalConfig {
    //id@TableId
    private Long id;

    //提交人
    private String submitUser;
    //流程名称
    private String approvalName;

}


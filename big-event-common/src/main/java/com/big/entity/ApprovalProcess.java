package com.big.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (ApprovalProcess)表实体类
 *
 * @author makejava
 * @since 2023-12-09 13:37:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_approval_process")
public class ApprovalProcess {
    //流程id@TableId
    private Integer id;

    //提交人
    private Integer submitUser;
    //是否被退回（0：正常；1：退回）
    private Integer returned;
    //状态（0：草稿，1：审核中，2：拒绝，3同意）
    private String status;
    //原因
    private String reason;
    //审核级别
    private Integer approvalLevel;


}


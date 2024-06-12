package com.big.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 审批记录表(ApprovalRecord)表实体类
 *
 * @author makejava
 * @since 2024-01-09 09:52:39
 */
@TableName("approval_record")
public class ApprovalRecord {
    //审批历史id@TableId
    private Long approvalRecordId;

    //审批id
    private Long approvalInfoId;
    //审批人
    private Long approvalUserId;
    //审批时间
    private Date approvalTime;
    //审批意见
    private String approvalOpinion;
    //0:未处理 ;1:同意 ;2:驳回;
    private String approvalStatus;

    private Integer node;

    public ApprovalRecord() {
    }

    public ApprovalRecord(Long approvalRecordId, Long approvalInfoId, Long approvalUserId, Date approvalTime, String approvalOpinion, String approvalStatus, Integer node) {
        this.approvalRecordId = approvalRecordId;
        this.approvalInfoId = approvalInfoId;
        this.approvalUserId = approvalUserId;
        this.approvalTime = approvalTime;
        this.approvalOpinion = approvalOpinion;
        this.approvalStatus = approvalStatus;
        this.node = node;
    }

    public Long getApprovalRecordId() {
        return approvalRecordId;
    }

    public void setApprovalRecordId(Long approvalRecordId) {
        this.approvalRecordId = approvalRecordId;
    }

    public Long getApprovalInfoId() {
        return approvalInfoId;
    }

    public void setApprovalInfoId(Long approvalInfoId) {
        this.approvalInfoId = approvalInfoId;
    }

    public Long getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(Long approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }
}


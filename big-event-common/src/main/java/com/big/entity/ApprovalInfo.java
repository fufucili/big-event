package com.big.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 审批主表(ApprovalInfo)表实体类
 *
 * @author makejava
 * @since 2024-01-09 09:51:58
 */
@TableName("approval_info")
public class ApprovalInfo {
    //审批id@TableId
    private Long approvalId;

    private Long approvalConfigId;

    //发起人
    private Long founder;
    //审批人集合
    private String approvalUser;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //0:处理中 ;1:已处理; 2:驳回;
    private String status;
    //附件
    private Integer file;
    //理由
    private String idea;

    public ApprovalInfo() {
    }

    public ApprovalInfo(Long approvalId, Long approvalConfigId, Long founder, String approvalUser, Date startTime, Date endTime, String status, Integer file) {
        this.approvalId = approvalId;
        this.approvalConfigId = approvalConfigId;
        this.founder = founder;
        this.approvalUser = approvalUser;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.file = file;
    }

    public ApprovalInfo(Long approvalId, Long approvalConfigId, Long founder, String approvalUser, Date startTime, Date endTime, String status, Integer file, String idea) {
        this.approvalId = approvalId;
        this.approvalConfigId = approvalConfigId;
        this.founder = founder;
        this.approvalUser = approvalUser;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.file = file;
        this.idea = idea;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public Long getApprovalConfigId() {
        return approvalConfigId;
    }

    public void setApprovalConfigId(Long approvalConfigId) {
        this.approvalConfigId = approvalConfigId;
    }

    public ApprovalInfo(Long approvalId, Long founder, String approvalUser, Date startTime, Date endTime, String status, Integer file) {
        this.approvalId = approvalId;
        this.founder = founder;
        this.approvalUser = approvalUser;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.file = file;
    }

    public Long getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
    }

    public Long getFounder() {
        return founder;
    }

    public void setFounder(Long founder) {
        this.founder = founder;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFile() {
        return file;
    }

    public void setFile(Integer file) {
        this.file = file;
    }
}


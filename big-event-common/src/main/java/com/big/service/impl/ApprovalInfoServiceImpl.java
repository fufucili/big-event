package com.big.service.impl;

import com.big.dto.AddApprovalDTO;
import com.big.entity.ApprovalInfo;
import com.big.entity.ApprovalRecord;
import com.big.mapper.ApprovalConfigMapper;
import com.big.mapper.ApprovalInfoMapper;
import com.big.mapper.ApprovalRecordMapper;
import com.big.service.ApprovalInfoService;
import com.big.utils.ResponseResult;
import com.big.utils.UserHolder;
import com.big.vo.MyApprovalInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 审批主表(ApprovalInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 09:51:58
 */
@Service("approvalInfoService")
public class ApprovalInfoServiceImpl implements ApprovalInfoService {
    @Autowired
    private ApprovalInfoMapper approvalInfoMapper;
    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;

    @Autowired
    private ApprovalConfigMapper approvalConfigMapper;

    /**
     * 新增审批
     */
    @Transactional
    @Override
    public ResponseResult addApproval(AddApprovalDTO addApproval) {
        //查询审批人集合
        StringBuilder reviewList = new StringBuilder();
        List<String> reviewUserList = approvalConfigMapper.selectApprovalReviewUserList(addApproval.getApprovalConfigId());
        Iterator<String> iterator = reviewUserList.iterator();
        while (iterator.hasNext()){
            reviewList.append(iterator.next());
            if(iterator.hasNext()){
                reviewList.append(",");
            }
        }
        //新增审批主表
        ApprovalInfo approvalInfo = new ApprovalInfo();
        approvalInfo.setFounder(UserHolder.getUser().getId());
        approvalInfo.setApprovalUser(reviewList.toString());
        approvalInfo.setStartTime(new Date());
        approvalInfo.setApprovalConfigId(addApproval.getApprovalConfigId());
        approvalInfo.setIdea(addApproval.getIdea());
        approvalInfoMapper.insertApprovalInfo(approvalInfo);

        //新增审批记录表
        ApprovalRecord approvalRecord = new ApprovalRecord();
        approvalRecord.setApprovalInfoId(approvalInfo.getApprovalId());
        approvalRecord.setApprovalUserId(Long.parseLong(reviewUserList.get(0)));
        approvalRecord.setNode(1);
        approvalRecordMapper.insertApprovalRecord(approvalRecord);

        return ResponseResult.okResult();
    }

    /**
     * 查询我的审批
     */
    @Override
    public List<MyApprovalInfoVo> getMyApproval() {
        Long userId = UserHolder.getUser().getId();
        List<MyApprovalInfoVo> approvalInfoList = approvalInfoMapper.selectMyApproval(userId);
        List<MyApprovalInfoVo> collect = approvalInfoList.stream().map(approval -> {
            if ("0".equals(approval.getStatus())) {
                approval.setApprovalStatus("处理中");
            } else if ("1".equals(approval.getStatus())) {
                approval.setApprovalStatus("同意");
            } else {
                approval.setApprovalStatus("驳回");
            }
            return approval;
        }).collect(Collectors.toList());
        return collect;
    }
}


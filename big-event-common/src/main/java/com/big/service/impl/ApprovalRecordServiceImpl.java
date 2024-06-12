package com.big.service.impl;

import com.big.entity.ApprovalInfo;
import com.big.entity.ApprovalRecord;
import com.big.exception.ServiceException;
import com.big.mapper.ApprovalInfoMapper;
import com.big.mapper.ApprovalRecordMapper;
import com.big.service.ApprovalRecordService;
import com.big.utils.ResponseResult;
import com.big.utils.UserHolder;
import com.big.vo.ApprovalVo;
import com.big.vo.MyHandleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 审批记录表(ApprovalRecord)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 09:52:39
 */
@Service("approvalRecordService")
public class ApprovalRecordServiceImpl implements ApprovalRecordService {
    @Autowired
    private ApprovalInfoMapper approvalInfoMapper;
    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;

    /**
     * 审批
     */
    @Transactional
    @Override
    public ResponseResult approval(ApprovalVo approvalVo) {
        //查询该条审批记录
        ApprovalRecord approvalRecord = approvalRecordMapper.getRecordById(approvalVo.getApprovalRecordId());

        //获取审批人和审批主表id
        Long approvalUserId = approvalRecord.getApprovalUserId();
        Long approvalInfoId = approvalRecord.getApprovalInfoId();

        //查询审批主表信息
        ApprovalInfo approvalInfo = approvalInfoMapper.getInfoById(approvalInfoId);
        //得到审批人列表
        String[] approval = approvalInfo.getApprovalUser().split(",");

        //判断当前登录人与审批人是否一致
        if (approvalUserId != UserHolder.getUser().getId()) {
            throw new ServiceException("审批人不对");
        }

        //该条审批未处理，且审批意见是同意时
        if (approvalVo.getStatus().equals("1") && approvalRecord.getApprovalStatus().equals("0")) {
            //当该审批人处于最后一个时
            if (approvalRecord.getNode().equals(approval.length)) {
                //更新审批记录表
                approvalRecord.setApprovalOpinion(approvalVo.getOpinion());
                approvalRecord.setApprovalStatus(approvalVo.getStatus());
                approvalRecord.setApprovalTime(new Date());

                //更新审批主表
                approvalInfo.setStatus("1");
                approvalInfo.setEndTime(new Date());

            } else {
                //更新审批记录表中的审批意见等
                approvalRecord.setApprovalOpinion(approvalVo.getOpinion());
                approvalRecord.setApprovalStatus(approvalVo.getStatus());
                approvalRecord.setApprovalTime(new Date());

                //新增下一条审批记录
                ApprovalRecord record = new ApprovalRecord();
                record.setNode(approvalRecord.getNode() + 1);
                record.setApprovalInfoId(approvalRecord.getApprovalInfoId());
                record.setApprovalUserId(Long.parseLong(approval[approvalRecord.getNode()]));
                approvalRecordMapper.insertApprovalRecord(record);
            }
        }
        //该条审批未处理，且审批意见是驳回时
        if (approvalVo.getStatus().equals("2") && approvalRecord.getApprovalStatus().equals("0")) {
            //更新审批记录表
            approvalRecord.setApprovalOpinion(approvalVo.getOpinion());
            approvalRecord.setApprovalStatus(approvalVo.getStatus());
            approvalRecord.setApprovalTime(new Date());

            //更新审批主表的状态
            approvalInfo.setStatus("2");
            approvalInfo.setEndTime(new Date());
        }
        approvalRecordMapper.updateRecord(approvalRecord);
        approvalInfoMapper.updateInfo(approvalInfo);

        return ResponseResult.okResult();
    }

    /**
     * 我的待审
     */
    @Override
    public List<MyHandleVo> getPending() {
        Long userId = UserHolder.getUser().getId();
        List<MyHandleVo> records = approvalRecordMapper.selectPending(userId);
        List<MyHandleVo> collect = records.stream().map(record -> {
            if ("0".equals(record.getApprovalStatus())) {
                record.setStatus("待处理");
            } else if ("1".equals(record.getApprovalStatus())) {
                record.setStatus("同意");
            } else {
                record.setStatus("驳回");
            }
            return record;
        }).collect(Collectors.toList());
        return collect;
    }
}


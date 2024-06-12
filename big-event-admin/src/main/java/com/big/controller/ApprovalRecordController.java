package com.big.controller;

import com.big.service.ApprovalRecordService;
import com.big.utils.ResponseResult;
import com.big.vo.ApprovalVo;
import com.big.vo.MyHandleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批记录表(ApprovalRecord)表控制层
 *
 * @author makejava
 * @since 2024-01-09 09:52:30
 */
@RestController
@CrossOrigin
@RequestMapping("/approvalRecord")
public class ApprovalRecordController {
    /**
     * 服务对象
     */
    @Resource
    private ApprovalRecordService approvalRecordService;

    /**
     * 查询我的审批列表
     *
     * 参数：登录人id(如果系统可以获取当前登录人id的话就可以不传）
     */
    @GetMapping("/pending")
    public ResponseResult pending(){
        List<MyHandleVo> records = approvalRecordService.getPending();
        return ResponseResult.okResult(records);
    }

    /**
     * 审批
     *
     * 参数：审批记录id
     * 更新审核记录表，视情况更新审批主表
     */
    @PutMapping("/approval")
    public ResponseResult approval(@RequestBody ApprovalVo approvalVo){
        return approvalRecordService.approval(approvalVo);
    }

}


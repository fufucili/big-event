package com.big.controller;

import com.big.dto.AddApprovalDTO;
import com.big.entity.ApprovalInfo;
import com.big.entity.Users;
import com.big.service.ApprovalInfoService;
import com.big.utils.ResponseResult;
import com.big.vo.MyApprovalInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批主表(ApprovalInfo)表控制层
 *
 * @author makejava
 * @since 2024-01-09 09:52:17
 */
@RestController
@RequestMapping("/approvalInfo")
public class ApprovalInfoController {
    /**
     * 服务对象
     */
    @Resource
    private ApprovalInfoService approvalInfoService;

    /**
     * 新增审批
     */
    @PostMapping("/addApproval")
    public ResponseResult addApproval(@RequestBody AddApprovalDTO addApproval){
        return approvalInfoService.addApproval(addApproval);
    }

    /**
     * 查询我的申诉
     */
    @GetMapping("/getApproval")
    public ResponseResult getMyApproval(){
        List<MyApprovalInfoVo> approvalInfoList = approvalInfoService.getMyApproval();
        return ResponseResult.okResult(approvalInfoList);
    }
}


package com.big.dto;

import lombok.Data;

import java.io.File;

/**
 * 新增申诉dto
 *
 * @author TuYongbin
 * @Date 2024/1/13 16:09
 */
@Data
public class AddApprovalDTO {
    private Long approvalConfigId;
    private String idea;
    private File file;
}

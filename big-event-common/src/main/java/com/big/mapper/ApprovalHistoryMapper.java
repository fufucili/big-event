package com.big.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.big.entity.ApprovalHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * (ApprovalHistory)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-09 13:39:57
 */
@Mapper
public interface ApprovalHistoryMapper extends BaseMapper<ApprovalHistory> {

}


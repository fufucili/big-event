package com.big.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Message)表实体类
 *
 * @author makejava
 * @since 2024-01-15 10:07:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_message")
public class Message {
    //id@TableId
    private Integer id;

    //来源模块
    private String sourceModule;
    //标题
    private String title;
    //内容
    private String content;
    //发送时间
    private Date sendTime;
    //接收人id
    private Integer receiverId;
    //接收人
    private String receiverName;
    //跳转路径
    private String url;
    //携带参数
    private String requestParams;
    //0：未读；1：已读
    private String read;
    //是否删除【0：未删；1：已删】
    private String deleted;
    //创建人
    private Integer createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Integer updateBy;
    //更新时间
    private Date updateTime;
    //备注
    private String remark;


}


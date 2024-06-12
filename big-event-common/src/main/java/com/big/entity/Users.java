package com.big.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户信息表(Users)表实体类
 *
 * @author makejava
 * @since 2023-11-21 16:55:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("big_users")
public class Users {
    @TableId
    private Long id;

    private String username;
    
    private String password;
    
    private String nickname;
    
    private String email;
    
    private String userPic;

    private String status;

}


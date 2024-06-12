package com.big.controller;

import com.big.entity.Users;
import com.big.service.IUsersService;
import com.big.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(Users)表控制层
 *
 * @author makejava
 * @since 2023-11-21 16:52:53
 */
@RestController
@RequestMapping("/user")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private IUsersService usersService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Users user){
        return usersService.register(user);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody Users user){
        return usersService.login(user);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout/{id}")
    public ResponseResult logout(@PathVariable("id") Long id){
        return usersService.logout(id);
    }

    /**
     * 注销用户
     */
    @PutMapping("/deleter/{id}")
    public ResponseResult deleter(@PathVariable("id") Long id){
        return usersService.deleter(id);
    }

    /**
     * 获取所有用户
     */
    @GetMapping("/getAll")
    public ResponseResult getAllUser(){
        List<Users> allUser = usersService.getAllUser();
        return ResponseResult.okResult(allUser);
    }
}


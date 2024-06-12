package com.big.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.big.dto.UserDTO;
import com.big.entity.Users;
import com.big.exception.ServiceException;
import com.big.mapper.UsersMapper;
import com.big.service.IUsersService;
import com.big.utils.RedisConstants;
import com.big.utils.ResponseResult;
import com.big.utils.UserHolder;
import com.big.vo.LoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户service实现
 *
 * @author TuYongbin
 * @Date 2023/11/21 17:02
 */
@Service
public class UsersServiceImpl implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     */
    @Override
    public ResponseResult register(Users user) {
        Users selectUser = usersMapper.selectUserByUsername(user.getUsername());
        if (Objects.nonNull(selectUser)) {
            throw new ServiceException("该用户已存在，请直接登录");
        }
        this.insertUser(user);
        return ResponseResult.okResult();
    }

    /**
     * 新增用户
     */
    @Override
    public Integer insertUser(Users user) {
        //密码md5加密
        String password = DigestUtils.md5Hex(user.getPassword().getBytes());
        user.setPassword(password);
        Integer result = usersMapper.insertUser(user);
        if (result < 1) {
            throw new ServiceException("新增用户失败");
        }
        return result;
    }

    /**
     * 用户登录
     */
    @Override
    public ResponseResult login(Users user) {
        //使用用户名查询用户
        Users users = usersMapper.selectUserByUsername(user.getUsername());
        if (Objects.isNull(users)) {
            throw new ServiceException("用户不存在，请先注册");
        }
        //校验密码
        String bePassword = DigestUtils.md5Hex(user.getPassword().getBytes());
        if (!users.getPassword().equals(bePassword)) {
            throw new ServiceException("密码不正确");
        }
        //生成token并存入redis
        String userToken = UUID.randomUUID().toString();
        String userTokenKey = RedisConstants.LOGIN_USER_KEY + userToken;
        UserDTO userDTO = BeanUtil.copyProperties(users, UserDTO.class);
        userDTO.setToken(userToken);

        Map<String, Object> map = BeanUtil.beanToMap(
                userDTO,
                new HashMap<>(),
                CopyOptions.create()
                        .ignoreNullValue()
                        .setFieldValueEditor((fileName, fileValue) -> fileValue.toString())
        );

        stringRedisTemplate.opsForHash().putAll(userTokenKey, map);
        stringRedisTemplate.expire(userTokenKey, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);

        Users userFront = new Users();
        BeanUtil.copyProperties(users, userFront, "password");
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(userToken);
        loginVo.setUser(userFront);

        return ResponseResult.okResult(loginVo);
    }

    /**
     * 根据用户id注销用户
     */
    @Override
    public ResponseResult deleter(Long id) {
        //更新状态
        Integer result = usersMapper.updateUserById(id);
        if (result < 1) {
            throw new ServiceException("注销账户失败");
        }
        return ResponseResult.okResult();
    }

    @Override
    public List<Users> getAllUser() {
        return usersMapper.selectAllUser();
    }

    /**
     * 退出登录
     */
    @Override
    public ResponseResult logout(Long id) {
        String token = UserHolder.getUser().getToken();
        stringRedisTemplate.delete(RedisConstants.LOGIN_USER_KEY + token);
        return ResponseResult.okResult();
    }
}

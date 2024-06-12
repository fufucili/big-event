package com.big.mapper;

import com.big.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户mapper
 *
 * @author TuYongbin
 * @Date 2023/11/21 17:01
 */
@Mapper
public interface UsersMapper {
    Users selectUserByUsername(String username);

    Integer insertUser(Users user);

    Integer updateUserById(Long id);

    List<Users> selectAllUser();

}

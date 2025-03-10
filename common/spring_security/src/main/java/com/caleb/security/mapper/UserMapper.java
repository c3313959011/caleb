package com.caleb.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caleb.security.entity.dto.DbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<DbUser>  {

    //根据用户名获取用户信息
    DbUser selectByUsername(String username);

    //根据邮箱信息获取用户信息
    DbUser selectByEmail(String email);

    //根据邮箱信息获取用户信息
    List<DbUser> selectByEmailAndUuid(String email,String uuid);

    //根据用户名获取用户信息
    boolean existsByName(String username);


    int insertUser(DbUser dbUser);
}

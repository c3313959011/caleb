package com.caleb.security.service;

import com.caleb.common_utils.Result;
import com.caleb.security.entity.vo.LoginReqVo;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    //登录之后返回token
    Result login(LoginReqVo loginReqVo, HttpServletRequest request);

}

package com.caleb.security.controller;


import com.caleb.common_utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 咕噜
 * @version 1.0
 * @description: 用户管理相关接口
 * @date 2025/3/9 17:31
 */

@RestController
@Api(tags = "用户管理模块")
@RequestMapping("/user")
public class UserManagerController {


    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info")
    public Result loginInfo(@ApiParam(value ="全局对象，security将信息设置到全局，通过这个就可以获取")
                            Principal principal){
//        if(principal == null){
//            return Result.error().message("用户信息不存在");
//        }
//        String username = principal.getName();//从全局中获取username
//        //根据用户名获取用户对象
//        DdUser ddUser =  ddUserService.getLoginInfoByUsername(username);
//        if(ddUser == null){
//            return Result.error().message("用户信息不存在");
//        }
//        List<DdRole> rolesByUserId = ddRoleService.getRolesByUserId(ddUser.getId());
//        ddUser.setRoles(rolesByUserId);
//        ddUser.setPassword(null);//无论如何都不可以返回密码
//        return Result.ok().message("用户信息获取成功").data("loginInfo",ddUser);
        return null;
    }
}

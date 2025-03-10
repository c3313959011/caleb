package com.caleb.security.controller;


import com.caleb.common_utils.Result;
import com.caleb.security.entity.vo.*;
import com.caleb.security.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 注册模块
 * @author 咕噜
 * @date 2025/3/10 22:22
 * @version 1.0
 */
@RestController
@RequestMapping("/register")
@Api(tags = "注册模块")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @ApiOperation(value = "判断能否注册")
    @PostMapping("/judge")
    public Result judgeRegister(@RequestBody RegisterJudgeReqVo registerVo, HttpServletRequest request){
        //获取待审核名单
        return registerService.judgeRegister(registerVo);
    }


    @ApiOperation(value = "提交注册")
    @PostMapping("/submit")
    public Result submitRegister(@RequestBody RegisterReqVo registerVo, HttpServletRequest request){
        //获取待审核名单
        return registerService.submitRegister(registerVo);
    }



}

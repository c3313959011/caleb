package com.caleb.home.controller;


import com.caleb.common_utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/normal")
@Api(tags = "普通用户模块")
public class NormalUserController {

    @ApiOperation(value = "普通用户测试")
    @GetMapping("/test")
    public Result test(){
        return Result.ok();
    }
}


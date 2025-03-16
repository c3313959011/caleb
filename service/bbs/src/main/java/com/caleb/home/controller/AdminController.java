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
@RequestMapping("/admin")
@Api(tags = "管理员模块")
public class AdminController {


    @ApiOperation(value = "管理员测试")
    @GetMapping("/test")
    public Result test(){
        return Result.ok();
    }

}


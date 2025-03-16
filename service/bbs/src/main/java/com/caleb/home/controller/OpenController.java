package com.caleb.home.controller;


import com.caleb.common_utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/11 22:55
 */

@RestController
@RequestMapping("/open")
@Api(tags = "开放模块")
public class OpenController {

    @ApiOperation(value = "开放模块测试")
    @GetMapping("/test")
    public Result test(){
        return Result.ok();
    }
}

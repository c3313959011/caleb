package com.caleb.security.controller;

import com.caleb.common_utils.Result;
import com.caleb.security.entity.vo.LoginReqVo;
import com.caleb.security.entity.vo.LoginResponseVo;
import com.caleb.security.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "登录模块")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;



    //登录之后返回token,如果想要带着token请求，需要添加Bearer前缀，Bearer token，中间空格分隔
    @ApiOperation(value = "用户登录")
    @PostMapping("/in")
    public Result<LoginResponseVo> login(@RequestBody LoginReqVo loginReqVo, HttpServletRequest request){
        return userService.login(loginReqVo,request);
    }



    @ApiOperation(value = "退出登录")
    @PostMapping("/out")
    public Result logout(){
        //这里省略了退出登录逻辑，比如销毁token，销毁数据库，redis中缓存等，因为没有引入redis，所以直接返回退出成功
        return Result.ok();
    }



    //以image/jpeg格式固定响应
    @ApiOperation(value = "获取验证码")
    @GetMapping(value = "/captcha",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response)  {
        userService.getCaptcha(request,response);
    }
}

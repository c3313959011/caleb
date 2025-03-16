package com.caleb.security.service.impl;


import com.caleb.common_utils.Result;
import com.caleb.common_utils.common_enum.ErrorCode;
import com.caleb.security.entity.dto.DbUser;
import com.caleb.security.entity.vo.LoginReqVo;
import com.caleb.security.entity.vo.LoginResponseVo;
import com.caleb.security.mapper.RoleUrlMapper;
import com.caleb.security.mapper.UserMapper;
import com.caleb.security.service.UserService;
import com.caleb.security.utils.JwtTokenUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.caleb.common_utils.constantParams.*;


@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;//PasswordEncoder，需要我们自己配置出来

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    DefaultKaptcha defaultKaptcha;



    @Override
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        /**定义response输出类型为image/jpeg类型**/
        response.setDateHeader("Expires",0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers(use addHeader)
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma","no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        /**生成验证码 begin**/
        String code = defaultKaptcha.createText();
        System.out.println("验证码："+code);
        //将验证码文本内容放入redis,
        //获取用户ip作为键值
        String remoteAddr = request.getRemoteAddr();
        //获取redis对象
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //设置验证码到redis，时限60秒，key为ip地址+"captcha"
        valueOperations.set(remoteAddr+"captcha",code,60, TimeUnit.SECONDS);
        //根据文本验证码内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(code);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出.jpg格式图片
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 登录之后返回token
     */
    @Override
    public Result login(LoginReqVo loginReqVo, HttpServletRequest request) {

        //校验参数合法性
        if (!userLoginCheck(loginReqVo)){
            return Result.errorEnum(ErrorCode.INVALID_ARGUMENT);
        }

        //从redis中获取验证码
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        String remoteAddr = request.getRemoteAddr();
        String captcha = (String)valueOperations.get(remoteAddr + "captcha");

        //如果验证码为空，或者验证码和redis中验证码不一致（忽略大小写比较），返回错误
        if(StringUtils.isEmpty(captcha)||!captcha.equalsIgnoreCase(loginReqVo.getCode())){
            return Result.errorEnum(ErrorCode.CAPTCHA_ERROR);
        }

        DbUser dbUser = userMapper.selectByUsername(loginReqVo.getUsername());
        //如果用户为空，
        if(dbUser==null){
            return Result.errorEnum(ErrorCode.USER_NOT_EXIT);
        }

        //密码匹配失败
        if(!passwordEncoder.matches(loginReqVo.getPassword(),dbUser.getPassword())){
            return Result.errorEnum(ErrorCode.ERROR_PASSWORD);
        }

//        //获取用户权限对应的URL列表
//        List<String> urls = new ArrayList<>();
//        if (dbUser.getUsername().equals(ADMIN)){
//            urls.add(ADMIN_ROLE);
//        }else{
//            urls = roleUrlMapper.getUrlListByRoleId(dbUser.getRoleId());
//        }
//        dbUser.setUrls(urls);
//
//        //更新登录用户对象UserDetails 如果获取对象成功，没有错误，那么使用Spring Security我们需要将对象设置到UserDetails中
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(dbUser,null,dbUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);//将其放在Security全局中

        //生成token，返回
        String jwtToken = jwtTokenUtil.getJwtToken(dbUser);
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        loginResponseVo.setToken(jwtToken);
        loginResponseVo.setTokenHead(tokenHead);
        return Result.ok().setData(loginResponseVo);
    }



    //参数校验
    public boolean userLoginCheck(LoginReqVo loginReqVo){
        if(loginReqVo == null){
            return false;
        }
        if(loginReqVo.getUsername()==null){
            return false;
        }
        if(loginReqVo.getPassword()==null){
            return false;
        }
        if(loginReqVo.getCode()==null){
            return false;
        }
        return true;
    }

}

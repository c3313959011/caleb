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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.caleb.common_utils.constantParams.*;


@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleUrlMapper roleUrlMapper;

    @Autowired
    PasswordEncoder passwordEncoder;//PasswordEncoder，需要我们自己配置出来

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RedisTemplate redisTemplate;



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

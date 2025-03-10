package com.caleb.security.exception;

import com.caleb.common_utils.Result;
import com.caleb.common_utils.common_enum.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当访问接口没有权限，自定义返回结果
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");//设置响应编码
        response.setContentType("application/json");//设置响应格式，前后端分离最好是json
        PrintWriter out = response.getWriter();//获取response输出流
        //创建返回结果对象
        Result result = Result.errorEnum(ErrorCode.NO_PERMISSION);
        //以json字符串形式放到response输出流中
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();//刷新
        out.close();//关闭流
    }
}

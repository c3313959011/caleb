package com.caleb.security.utils;

import com.caleb.security.entity.dto.DbUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUserUtils {
    /**
     * 获取SpringSecurity全局上下文中的user对象
     * @return
     */
    public static DbUser getSpringSecurityUser(){
        return (DbUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

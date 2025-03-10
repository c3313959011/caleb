package com.caleb.security.filter;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求url分析所需角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取FilterInvocation对象
        FilterInvocation filterInvocation = (FilterInvocation) o;
        //获取请求url
        String requestUrl = filterInvocation.getRequestUrl();
        return SecurityConfig.createList(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

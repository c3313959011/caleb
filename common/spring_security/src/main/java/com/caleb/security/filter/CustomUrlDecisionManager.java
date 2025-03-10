package com.caleb.security.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 权限控制
 * 判断用户角色
 */
import java.util.Collection;

import static com.caleb.common_utils.constantParams.ADMIN_ROLE;

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    /**
     *
     * @param authentication 当前访问用户
     * @param o
     * @param collection Collection<ConfigAttribute> 我们在上一个过滤器CustomFilter implements FilterInvocationSecurityMetadataSource中配置了
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //用户登录时存放的该用户拥有的URL
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //遍历ConfigAttribute
        for(ConfigAttribute configAttribute : collection){
            String url = configAttribute.getAttribute();
            for(GrantedAuthority authority:authorities){
                if (authority.getAuthority().equals(ADMIN_ROLE)){
                    return;
                }
                if (url.contains(authority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!!!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

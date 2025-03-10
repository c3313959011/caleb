package com.caleb.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caleb.security.entity.DdRole;
import com.caleb.security.entity.dto.DbUser;
import com.caleb.security.exception.RestAccessDeniedHandler;
import com.caleb.security.exception.RestAuthorizationEntryPoint;
import com.caleb.security.filter.CustomFilter;
import com.caleb.security.filter.CustomUrlDecisionManager;
import com.caleb.security.filter.JwtAuthencationTokenFilter;
import com.caleb.security.mapper.RoleUrlMapper;
import com.caleb.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

import static com.caleb.common_utils.constantParams.ADMIN;
import static com.caleb.common_utils.constantParams.ADMIN_ROLE;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DdUserService ddUserService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleUrlMapper roleUrlMapper;

    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;//当访问接口没有权限，自定义返回结果

    @Autowired
    RestAuthorizationEntryPoint restAuthorizationEntryPoint;//当未登录或token失效时访问接口，自定义返回结果

    @Autowired
    CustomFilter customFilter;//根据请求url分析所需角色过滤器

    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;//判断用户角色是否可以访问url过滤器
    /**
     * 配置密码解析Bean实例
     * 加载到IOC容器
     * 这是Spring Security 自定义登录逻辑的硬性要求
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置UserDetailsService
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username->{
            //高版本发生循环依赖
            DbUser user = userMapper.selectByUsername(username);
            if(user == null){
                throw new UsernameNotFoundException("用户名或密码不正确");
            }
            //获取用户权限对应的URL列表
            List<String> urls = new ArrayList<>();
            if (user.getUsername().equals(ADMIN)){
                urls.add(ADMIN_ROLE);
            }else{
                urls = roleUrlMapper.getUrlListByRoleId(user.getRoleId());
            }
            user.setUrls(urls);
            return user;
        };
    }

    /**
     * 配置JWT登录授权过滤器
     */
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())//设置Security使用的userDetailsService，为我们上面配置的
                .passwordEncoder(passwordEncoder());//设置使用的passwordEncoder为我们上面配置的passwordEncoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用JWT不需要csrg
        http.csrf().disable()
                //使用Token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()//接下来配置授权
                .authorizeRequests()
                .anyRequest().authenticated()//除了上面配置的，剩下的请求全部拦截，必须认证通过才能访问
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {//动态权限配置
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(customFilter);//根据请求url分析所需角色过滤器
                        o.setAccessDecisionManager(customUrlDecisionManager);//判断用户角色是否可以访问url过滤器
                        return o;
                    }
                })
                .and()//接下来配置缓存
                .headers()
                .cacheControl()
                ;
        //添加JWT登录授权过滤拦截器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权，和未登录结果返回，前后端分离，需要返回状态码
        http.exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);

    }

    /**
     * 放行路径配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/login/**",
                        "/register/**",
                        "/css/**",
                        "/js/**",
                        "/doc.html",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                );
    }
}

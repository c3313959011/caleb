package com.caleb.service_base.config;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc//表中此类为Swagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        List<Parameter> pars = new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")//分组名
                .apiInfo(webApiInfo())//在线文档的信息，传入ApiInfo对象，就是下面内个方法返回的对象
                .select()
                .build()
                //因为我们开启了securityJWT验证，所以我们也需要给Swagger配置头信息
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes())
                .ignoredParameterTypes(BasicErrorController.class);
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("caleb接口文档")
                .description("苹果老家")
                .termsOfServiceUrl("待定")
                .version("1.0")
                .contact(new Contact("name","url","email"))
                .build();
    }


    private List<ApiKey> securitySchemes(){
        //设置头信息
        ArrayList<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        result.add(apiKey);
        return result;
    }

    /**
     * 设置哪些路径需要授权
     * @return
     */
    private List<SecurityContext> securityContexts(){
        //设置需要授权认证的路径
        ArrayList<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/.*"));
////        result.add(getContextByPath("/login/.*"));
//        result.add(getContextByPath("/login/info"));
//        result.add(getContextByPath("/logout"));
//        result.add(getContextByPath("/security/*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex(pathRegex))
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        ArrayList<SecurityReference> result = new ArrayList<>();
        //设置授权范围为全局global
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");

        //官方要求必须放在数组中，所以将其放在数组中
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        //官方要求List中添加new SecurityReference("Authorization",authorizationScopes)
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }
}

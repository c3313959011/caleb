package com.caleb.service_animation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.caleb")
//@ComponentScan("com.dd.security.service")
//@ComponentScan("com.dd.security.config")
//@ComponentScan("com.dd.security.exception")
//@ComponentScan("com.dd.security.filter")
//@ComponentScan("com.dd.security.utils")
//@ComponentScan("com.dd.security.service.impl")
@MapperScan("com.caleb.service_animation.mapper")
@MapperScan("com.caleb.security.mapper")
public class ServiceAnimationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAnimationApplication.class,args);
    }
}

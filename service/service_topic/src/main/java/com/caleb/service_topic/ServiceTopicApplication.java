package com.caleb.service_topic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.caleb")
@MapperScan("com.caleb.service_topic.mapper")
@MapperScan("com.caleb.security.mapper")
public class ServiceTopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTopicApplication.class,args);
    }
}

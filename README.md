# SpringSecurity-scaffolding

#### 介绍
基于springSecurity的权限认证脚手架 
相关博客地址：[https://blog.csdn.net/grd_java/article/details/121925792](https://blog.csdn.net/grd_java/article/details/121925792)

#### 软件架构
需要redis


#### 安装教程

1.  下载源码到idea
2.  设置JDK为1.8版本，11版本需要引入额外jar包
3.  执行sql语句，有个oauth.sql文件，默认数据库名字为oauth
4.  启动redis
5.  redis配置位置
6.  启动测试微服务ServiceApplication
7.  访问http://localhost:8001/doc.html进行测试（1. 获取验证码，2.通过admin，密码admin登录获取token和token指定前缀Bearer，将其设置到请求头中）
8.   获取到前缀Bearer和token字符串后，以Bearer token字符串的格式，设置到请求头
9.   接下来即可开始测试需要认证的接口
10.  无需登录即可访问注册、登录、开放模块;admin用户可访问所有模块，普通用户可访问普通用户模块
11.  管理员账号 admin admin 普通用户账号 cm 123456
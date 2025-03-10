package com.caleb.security.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 咕噜
 * @version 1.0
 * @description: 注册请求类
 * @date 2025/3/9 12:21
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "注册请求体",description = "注册请求体")
public class RegisterReqVo {

    @ApiModelProperty(value = "邮箱地址",required = true)
    private String email;

    @ApiModelProperty(value = "游戏ID",required = true)
    private String uuid;

    @ApiModelProperty(value = "用户名",required = true)
    private String name;

    @ApiModelProperty(value = "用户密码",required = true)
    private String password;

    @ApiModelProperty(value = "注册码",required = true)
    private String captcha;

}

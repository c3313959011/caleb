package com.caleb.security.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/9 19:34
 */

@Data
@ApiModel(value = "用户登录对象",description = "")
public class LoginResponseVo {

    @ApiModelProperty(value = "token",required = true)
    private String token;

    @ApiModelProperty(value = "tokenHead",required = true)
    private String tokenHead;
}

package com.caleb.security.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/9 14:55
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "答题请求体",description = "答题请求体")
public class ExamReqVo {

    @ApiModelProperty(value = "邮箱地址",required = true)
    private String email;

    @ApiModelProperty(value = "游戏ID",required = true)
    private String uuid;
}

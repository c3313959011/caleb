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
 * @date 2025/3/16 21:09
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "答题信息",description = "答题信息")
public class ExamInfoVo {

    @ApiModelProperty(value = "题目序号",required = true)
    private int id;

    @ApiModelProperty(value = "题目信息",required = true)
    private String question;

    @ApiModelProperty(value = "A选项",required = true)
    private String answerA;

    @ApiModelProperty(value = "B选项",required = true)
    private String answerB;

    @ApiModelProperty(value = "C选项",required = true)
    private String answerC;

    @ApiModelProperty(value = "D选项",required = true)
    private String answerD;

}

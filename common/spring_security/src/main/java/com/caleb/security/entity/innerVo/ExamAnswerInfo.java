package com.caleb.security.entity.innerVo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/16 22:36
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "答案信息",description = "答案信息")
public class ExamAnswerInfo {

    @ApiModelProperty(value = "题目序号",required = true)
    private int id;


    @ApiModelProperty(value = "正确选项",required = true)
    private String CorrectAnswer;
}

package com.caleb.security.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/16 21:06
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "答题响应体",description = "答题信息")
public class ExamResponseVo {

    @ApiModelProperty(value = "答题编码",required = true)
    private String examCode;

    @ApiModelProperty(value = "问卷信息",required = true)
    private List<ExamInfoVo> examInfoList;

    public ExamResponseVo(String examCode, List<ExamInfoVo> examInfoList) {
        this.examCode = examCode;
        this.examInfoList = examInfoList;
    }
}

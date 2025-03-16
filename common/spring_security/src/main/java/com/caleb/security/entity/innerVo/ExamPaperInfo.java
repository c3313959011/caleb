package com.caleb.security.entity.innerVo;


import com.caleb.security.entity.vo.ExamInfoVo;
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
 * @date 2025/3/16 22:23
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "答卷信息",description = "界面唯一标识（答题码）和答案")
public class ExamPaperInfo {

    @ApiModelProperty(value = "答题编码",required = true)
    private String examCode;

    @ApiModelProperty(value = "答案信息",required = true)
    private List<ExamAnswerInfo> examAnswerList;

    public ExamPaperInfo(String examCode, List<ExamAnswerInfo> examAnswerList) {
        this.examCode = examCode;
        this.examAnswerList = examAnswerList;
    }
}

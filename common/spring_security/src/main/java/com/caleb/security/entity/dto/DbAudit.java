package com.caleb.security.entity.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DbAudit对象", description="审核信息表")
public class DbAudit implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "游戏ID")
    private String uuid;

    @ApiModelProperty(value = "自证图片路径")
    private int picPath;

    @ApiModelProperty(value = "审核状态")
    private int status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "注册码")
    private String captcha;

    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    @ApiModelProperty(value = "审核员")
    private String operator;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}

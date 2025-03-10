package com.caleb.security.entity.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.sql.Date;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/9 21:00
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DbRoleUrl对象", description="角色URL权限表")
@TableName("tbl_role_url")
public class DbRoleUrl {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "权限ID")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

}

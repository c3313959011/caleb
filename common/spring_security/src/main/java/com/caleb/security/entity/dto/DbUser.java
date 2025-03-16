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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 咕噜
 * @version 1.0
 * @description: 用户信息表
 * @date 2025/3/9 15:15
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DbUser对象", description="用户信息表")
@TableName("tbl_user")
public class DbUser implements UserDetails {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "个人签名")
    @TableField("signature")
    private String signature;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "游戏ID")
    @TableField("uuid")
    private String uuid;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "用户URL")
    @TableField(exist = false)
    private List<String> urls;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(urls!=null){
            List<SimpleGrantedAuthority> authorities =
                    urls.stream()
                            //将每一个角色，遍历成Security指定的权限字符对象，
                            // 比如ROLE_admin要封装成new SimpleGrantedAuthority("ROLE_admin")
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());//然后返回为list
            return authorities;//将角色权限返回
        }else{
            return new ArrayList<SimpleGrantedAuthority>();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

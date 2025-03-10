package com.caleb.security.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caleb.security.entity.dto.DbRoleUrl;
import com.caleb.security.entity.dto.DbUser;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/9 21:00
 */

@Mapper
public interface RoleUrlMapper extends BaseMapper<DbRoleUrl> {

    List<String> getUrlListByRoleId(@Param("roleId") int roleId);
}

package com.caleb.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caleb.security.entity.dto.DbAudit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuditMapper extends BaseMapper<DbAudit>  {

    //查询待审核信息中，邮箱和UUID是否存在
    boolean checkUserValid(@Param("uuid") String uuid,@Param("email") String email,@Param("status") int status);


    //查询待审核信息中，邮箱和UUID是否存在
    DbAudit checkInfoByEmail(@Param("email") String email);

}

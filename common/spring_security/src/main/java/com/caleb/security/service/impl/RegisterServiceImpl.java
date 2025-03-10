package com.caleb.security.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.caleb.common_utils.Result;
import com.caleb.common_utils.common_enum.ErrorCode;
import com.caleb.common_utils.common_enum.RegisterStatus;
import com.caleb.common_utils.common_enum.RoleEnum;
import com.caleb.security.entity.dto.DbAudit;
import com.caleb.security.entity.dto.DbUser;
import com.caleb.security.entity.vo.*;
import com.caleb.security.mapper.*;
import com.caleb.security.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 注册类
 * @author 咕噜
 * @date 2025/3/9 0:06
 * @version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    AuditMapper auditMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    // 正则表达式：校验邮箱格式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    //判断能否注册
    @Override
    public Result judgeRegister(RegisterJudgeReqVo registerVo) {
        //1.检查邮箱和UUID是否已在用户表中
        //2.检查邮箱和UUID是否已在审批表中
        //3.检查待审核和正在答题的人数是否超出审批最大数
        return Result.ok();
    }
    

    //提交注册请求
    @Override
    public Result submitRegister(RegisterReqVo registerVo) {
        //1.检查请求参数是否合法
        if (!submitRegisterCheck(registerVo)){
            return Result.errorEnum(ErrorCode.INVALID_ARGUMENT);
        }

        String email = registerVo.getEmail();
        String uuid = registerVo.getUuid();
        //2.检查邮箱和UUID是否已在审核表中
        DbAudit dbAudit = auditMapper.checkInfoByEmail(email);
        //账号不在审核表
        if (ObjectUtil.isEmpty(dbAudit)){
            return Result.errorEnum(ErrorCode.REGISTER_INFO_NOT_EXIT);
        }
        //检查审核表中记录的UUID是否一致
        if(!dbAudit.getUuid().equals(uuid)){
            return Result.errorEnum(ErrorCode.REGISTER_INFO_EXCEPTION);
        }

        //3.检查审核状态
        if(dbAudit.getStatus() == RegisterStatus.AUDIT_FAILED.getCode()){
            return Result.errorEnum(ErrorCode.AUDIT_FAILED);
        }
        if(dbAudit.getStatus() == RegisterStatus.WAIT_AUDIT.getCode()){
            return Result.errorEnum(ErrorCode.WAIT_AUDIT);
        }

        //4.检查注册标识是否一致
        if (dbAudit.getCaptcha()==null || !dbAudit.getCaptcha().equals(registerVo.getCaptcha())){
            return Result.errorEnum(ErrorCode.REGISTER_CODE_ERROR);
        }


        //5.查询用户信息表中是否重复
        List<DbUser> dbUserList = userMapper.selectByEmailAndUuid(email, registerVo.getUuid());
        if (dbUserList.size() >= 2){
            return Result.errorEnum(ErrorCode.REGISTER_INFO_EXCEPTION);
        }
        if (dbUserList.size() == 1){
            if (dbUserList.get(0).getEmail().equals(email) && dbUserList.get(0).getUuid().equals(uuid)){
                return Result.errorEnum(ErrorCode.REGISTER_USER_EXIT);
            }else{
                return Result.errorEnum(ErrorCode.REGISTER_INFO_EXCEPTION);
            }
        }

        //6.检查用户信息表中是否重名
        if (userMapper.existsByName(registerVo.getName())){
            return Result.errorEnum(ErrorCode.NAME_DUPLICATE);
        }

        //7.注册至用户信息
        String password = passwordEncoder.encode(registerVo.getPassword());
        DbUser dbUser = new DbUser();
        dbUser.setUsername(registerVo.getName());
        dbUser.setPassword(password);
        dbUser.setEmail(email);
        dbUser.setUuid(uuid);
        dbUser.setRoleId(RoleEnum.USER.getCode());
        userMapper.insertUser(dbUser);

        return Result.ok();
    }



    //参数校验
    public boolean submitRegisterCheck(RegisterReqVo registerVo){
        String email = registerVo.getEmail();
        String uuid = registerVo.getUuid();
        String code = registerVo.getCaptcha();
        if (email == null || email.isEmpty() || !email.matches(EMAIL_REGEX)){
            return false;
        }
        if (uuid == null || uuid.length() != 10){
            return false;
        }
        if (code == null || code.length() != 32){
            return false;
        }
        if (registerVo.getName() == null || registerVo.getName().length() > 32){
            return false;
        }
        if (registerVo.getPassword() == null || registerVo.getPassword().length() > 32){
            return false;
        }
        return true;
    }
}

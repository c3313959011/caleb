package com.caleb.common_utils.common_enum;

import lombok.Getter;

/**
 * @description: 错误码枚举
 * @author 咕噜
 * @date 2025/3/9 13:46
 * @version 1.0
 */

@Getter
public enum ErrorCode {

    //1000-1010：通用错误码
    SUCCESS(200,"成功"),
    INVALID_ARGUMENT(1000,"非法参数"),
    NO_PERMISSION(1001,"用户无权限"),

    //1011-1020：用户登录
    USER_NOT_LOGIN(1011,"用户未登录"),
    ERROR_PASSWORD(1012,"账号密码错误"),
    USER_NOT_EXIT(1013,"用户不存在"),
    CAPTCHA_ERROR(1014,"验证码错误"),

    //1021-1030：用户注册
    REGISTER_INFO_NOT_EXIT(1021,"注册信息不存在,请先答题注册"),
    WAIT_AUDIT(1022,"等待审核中"),
    REGISTER_USER_EXIT(1023,"账号已注册，请直接登录"),
    REGISTER_INFO_EXCEPTION(1024,"UUID与邮箱不匹配，请检查注册信息或联系管理员"),
    AUDIT_FAILED(1024,"账号审核失败，请重新答题并更新自证"),
    REGISTER_CODE_ERROR(1025,"注册码异常"),
    NAME_DUPLICATE(1026,"用户名重复")
    ;

    private final int code;
    private final String message;


    ErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }


}

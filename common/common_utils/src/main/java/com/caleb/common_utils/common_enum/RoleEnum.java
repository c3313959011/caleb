package com.caleb.common_utils.common_enum;

public enum RoleEnum {

    SUPER_ADMIN(1,"超级管理员"),
    ADMIN(2,"普通管理员"),
    USER(3,"普通用户");

    private final int code;
    private final String message;


    RoleEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}

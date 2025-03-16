package com.caleb.common_utils.common_enum;

/**
 * @description: 注册状态枚举
 * @author 咕噜
 * @date 2025/3/9 13:46
 * @version 1.0
 */
public enum RegisterStatus {

    WAIT_AUDIT(0,"待审核"),
    AUDIT_SUCCESS(1,"审核成功"),
    AUDIT_FAILED(2,"审核失败"),
    WAIT_EXAM(3,"考试中")
    ;

    private final int code;
    private final String message;


    RegisterStatus(int code,String message){
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

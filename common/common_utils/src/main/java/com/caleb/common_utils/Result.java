package com.caleb.common_utils;

import com.caleb.common_utils.common_enum.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> {

    @ApiModelProperty(value="状态码")
    public Integer code;

    @ApiModelProperty(value="返回消息")
    public String message;

    @ApiModelProperty(value="返回数据（暂时不用未删除）")
    public Map<String,Object> data1=new HashMap<>();

    @ApiModelProperty(value="返回数据")
    public T data;

    /**处理响应成功的静态方法**/
    public static Result ok(){
        Result result=new Result();
        result.setCode(ErrorCode.SUCCESS.getCode()); //调用我们提供状态码的接口，设置状态码
        result.setMessage(ErrorCode.SUCCESS.getMessage());          //设置提示信息
        return result;                      //返回对象，外键不能new，只能通过静态方法获取对象
    }
    /**处理响应失败的静态方法**/
    public static Result error(){
        Result result=new Result();
        result.setCode(ResultCode.ERROR); //调用我们提供状态码的接口，设置状态码
        result.setMessage("失败");        //设置提示信息
        result.setData("");
        return result;
    }

    /**处理响应失败的静态方法**/
    public static Result errorEnum(ErrorCode errorCode){
        Result result=new Result();
        result.setCode(errorCode.getCode()); //调用我们提供状态码的接口，设置状态码
        result.setMessage(errorCode.getMessage());        //设置提示信息
        result.setData("");
        return result;
    }


    public Result code(Integer i){
        this.code=i;
        return this;
    }

    public Result message(String str){
        this.message=str;
        return this;
    }

    public Result data(String key ,Object value){
        this.data1.put(key,value);
        return this;
    }

    public Result<T> setData(T data){
        this.data = data;
        return this;
    }

}
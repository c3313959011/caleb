package com.caleb.common_utils;


/**
 * @author 咕噜
 * @version 1.0
 * @description:
 * @date 2025/3/16 22:44
 */
public class RedisKey {

    //问卷缓存的redis信息，用于记录打乱的答案
    public static final String EXAM_INFO = "exam:<email>";
    public static final String EXAM_INFO_REPLACE = "<email>";

}

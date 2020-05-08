package com.example.sys.demo.entity;

import lombok.Data;

/**
 * @author 陈莉
 */
@Data
public class Result<T> {
    // 状态码
    private Integer code;

    // 状态描述信息
    private String message;

    // 定义为范型
    private T data;

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + message + '\"' +
                ", \"data\":\"" + data + '\"'+
                '}';
    }
}

package com.example.sys.demo.VO;

import lombok.Data;

/**
 * @author 陈莉
 */
@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;

    /**错误提示*/
    private String msg;

    private T data;

}

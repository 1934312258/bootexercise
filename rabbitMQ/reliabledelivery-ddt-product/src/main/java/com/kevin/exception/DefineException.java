package com.kevin.exception;

import lombok.Data;

/**
 * @author kevin
 * @date 2019-11-12 10:53
 * @description todo
 **/
@Data
public class DefineException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public DefineException(Integer code, String errMsg) {
        super();
        this.code = code;
        this.errMsg = errMsg;
    }
}

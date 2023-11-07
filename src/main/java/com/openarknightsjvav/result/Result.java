package com.openarknightsjvav.result;

import lombok.Data;

/**
 * ClassName: Status
 * Package: com.openarknightsjvav.result
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/6 21:59
 * @Version 1.0
 */
@Data
public class Result {
    private int status;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data){
        return new Result(0,"OK", data);
    }
}

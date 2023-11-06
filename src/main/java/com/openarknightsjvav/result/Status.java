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
public class Status {
    private int status;
    private String msg;
    private Object data;

    public Status() {
    }

    public Status(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Status success(Object data){
        return new Status(0,"OK", data);
    }
}

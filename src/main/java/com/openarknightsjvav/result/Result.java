package com.openarknightsjvav.result;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * ClassName: syncDataResult
 * Package: com.cryrain.openarknightsjava.result
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/2 19:19
 * @Version 1.0
 */
@Data
public class Result {
    private Integer result;
    private Long ts;
    private Object user;
    private LinkedHashMap playerDataDelta;


    public Result() {
    }

    public Result(Integer result, Long ts, Object user, LinkedHashMap playerDataDelta) {
        this.result = result;
        this.ts = ts;
        this.user = user;
        this.playerDataDelta = playerDataDelta;
    }


    public static Result sendSyncData(Object user) {
        Long time = System.currentTimeMillis() / 1000;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("modified", new HashMap());
        linkedHashMap.put("deleted", new HashMap());
        return new Result(0, time, user, linkedHashMap);
    }


}
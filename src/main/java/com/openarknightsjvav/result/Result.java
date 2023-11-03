package com.openarknightsjvav.result;

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
        linkedHashMap.put("modified",new HashMap());
        linkedHashMap.put("deleted",new HashMap());
        return new Result(0 ,time ,user ,linkedHashMap);
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public LinkedHashMap getPlayerDataDelta() {
        return playerDataDelta;
    }

    public void setPlayerDataDelta(LinkedHashMap playerDataDelta) {
        this.playerDataDelta = playerDataDelta;
    }
}

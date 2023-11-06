package com.openarknightsjvav.controller;

import com.openarknightsjvav.result.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: UserController
 * Package: com.openarknightsjvav.controller
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/6 22:36
 * @Version 1.0
 */
@RestController
@Slf4j
public class UserController {

    @Autowired


    @PostMapping("/user/oauth2/v2/grant")
    public Status getGrant(){
        log.info("------/user/oauth2/v2/grant------");
        return Status.success(Map.of("code", "doctorate", "uid", "1"));//UID
    }

    @PostMapping("/u8/user/v1/getToken")
    public Map GetToken(){
        log.info("------/u8/user/v1/getToken------");
        return Map.of("channelUid", "1", "error", "",
                "extension", "{\\\"isMinor\\\": false, \\\"isAuthenticate\\\": true}",
                "isGuest", 0,
                "result", 0,
                "token", "abcd",
                "uid", "1");
    }

    @PostMapping("/u8/pay/getAllProductList")
    public Map getAllProductList(){
        log.info("------/u8/pay/getAllProductList------");
        return Map.of("productList", new ArrayList<>());
    }

    @PostMapping("/account/login")
    public Map postLogin(){
        log.info("------/account/login------");
        return Map.of("result", 0, "uid", UUID.randomUUID(), "secret", "yostar", "serviceLicenseVersion", 0);
    }

    @PostMapping("/pay/getUnconfirmedOrderIdList")
    public Map getUnconfirmedOrderIdList(){
        log.info("------/pay/getUnconfirmedOrderIdList------");
        return Map.of("orderIdList", new ArrayList<>(), "playerDataDelta", Map.of("deleted", new HashMap<>(),
        "modified", new HashMap<>()));
    }

    @PostMapping("/account/syncStatus")
    public Map getSyncStatus(){
        log.info("------/account/syncStatus------");
        return Map.of("ts", System.currentTimeMillis() / 1000,
                "result", new HashMap<>(), "playerDataDelta",
                Map.of("modified", new HashMap(),"deleted",new HashMap<>()));
    }
}

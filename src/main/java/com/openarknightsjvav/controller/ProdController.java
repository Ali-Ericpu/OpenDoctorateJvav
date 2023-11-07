package com.openarknightsjvav.controller;

import com.openarknightsjvav.result.Result;
import com.openarknightsjvav.service.ProdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * ClassName: ProdController
 * Package: com.openarknightsjvav.controller
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/6 18:44
 * @Version 1.0
 */
@RestController
@Slf4j
public class ProdController {

    @Autowired
    private ProdService prodService;

    @GetMapping("/config/prod/official/network_config")
    public String getNetworkConfig() {
//        Map networkConfig = prodService.getNetworkConfig();
        log.info("------/config/prod/official/network_config------");
        return prodService.getNetworkConfig();
    }

    @GetMapping("/config/prod/official/remote_config")
    public Map getRemote() throws IOException {
        log.info("------/config/prod/official/remote_config------");
        return prodService.getRemote();
    }

    @GetMapping("/config/prod/official/Android/version")
    public Map getVersion() throws IOException {
        log.info("------/config/prod/official/Android/version------");
        return prodService.getVersion();
    }

    @GetMapping("/app/v1/config")
    public Result getAPPConfig() throws IOException {
        log.info("------/app/v1/config------");
        Map Version = prodService.getAPPConfig();
        return Result.success(Version);
    }

    @GetMapping("/general/v1/server_time")
    public Result getServerTime() {
        log.info("------/general/v1/server_time------");
        return Result.success(Map.of("serverTime", System.currentTimeMillis() / 1000,
                "isHoliday", false));
    }

    @GetMapping("/config/prod/announce_meta/Android/preannouncement.meta.json")
    private Map getNotice() throws IOException {
        log.info("------/config/prod/announce_meta/Android/preannouncement.meta.json------");
        return prodService.getNotice();
    }

    @GetMapping("/user/info/v1/basic")
    public Result getBasic(){
        log.info("------/user/info/v1/basic------");
        return Result.success(prodService.getBasic());
    }
}

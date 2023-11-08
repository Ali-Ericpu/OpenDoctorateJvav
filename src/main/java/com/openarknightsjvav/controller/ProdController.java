package com.openarknightsjvav.controller;

import com.openarknightsjvav.result.Result;
import com.openarknightsjvav.service.ProdService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
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
public class ProdController {

    @Autowired
    private ProdService prodService;

    @GetMapping("/config/prod/official/network_config")
    public String getNetworkConfig() {
//        Map networkConfig = prodService.getNetworkConfig();
        return prodService.getNetworkConfig();
    }

    @GetMapping("/config/prod/official/remote_config")
    public Map getRemote() throws IOException {
        return prodService.getRemote();
    }

    @GetMapping("/config/prod/official/Android/version")
    public Map getVersion() throws IOException {
        return prodService.getVersion();
    }

    @GetMapping("/app/v1/config")
    public Result getAPPConfig() throws IOException {
        Map Version = prodService.getAPPConfig();
        return Result.success(Version);
    }

    @GetMapping("/general/v1/server_time")
    public Result getServerTime() {
        return Result.success(Map.of("serverTime", System.currentTimeMillis() / 1000,
                "isHoliday", false));
    }

    @GetMapping("/config/prod/announce_meta/Android/preannouncement.meta.json")
    private Map getNotice() throws IOException {
        return prodService.getNotice();
    }

    @GetMapping("/user/info/v1/basic")
    public Result getBasic(){
        return Result.success(prodService.getBasic());
    }

    @GetMapping ("/assetbundle/official/Android/assets/{assetsHash}/{fileName}")
    public ResponseEntity<InputStreamResource> asset(@PathVariable String assetsHash,@PathVariable String fileName) throws IOException {
        return prodService.getAsset(assetsHash, fileName);
    }
}

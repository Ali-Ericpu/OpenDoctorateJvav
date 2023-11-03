package com.openarknightsjvav.controller;

import com.openarknightsjvav.result.Result;
import com.openarknightsjvav.service.SyncDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * ClassName: SyncDataController
 * Package: com.cryrain.openarknightsjava.controller
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/2 18:54
 * @Version 1.0
 */
@RestController
@Slf4j
public class SyncDataController {

    @Autowired
    private SyncDataService syncDataService;

    @PostMapping("/account/syncData")
    public Result getSyncData() throws IOException {
        LinkedHashMap syncData = syncDataService.getSyncData();
        return Result.sendSyncData(syncData);
    }
}

package com.openarknightsjvav.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * ClassName: Confing
 * Package: com.openarknightsjvav.utils
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/4 17:03
 * @Version 1.0
 */
public class Confing {

    public static Map getConfig() throws IOException {
        String loadConfig = FileUtils.readFileToString(new File("src/main/resources/data/config/config.json"), "utf-8");
        Map<String, Object> mapConfig = JsonUtils.transferToMap(loadConfig);
        return mapConfig;
    }


    public static Map getCharConfig() throws IOException {
        Map Charconfig =(Map) getConfig().get("charConfig");
        return Charconfig;
    }
}

package com.openarknightsjvav;

import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class OpenarknightsjvavApplicationTests {

    @Test
        public void getStages() throws IOException {
        String stage = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"));
        Map<String, Object> map = JsonUtils.transferToMap(stage);
//        System.out.println(map);
        Map stages = (Map) map.get("stages");
//        System.out.println(stages);
        ArrayList list = new ArrayList();
        ArrayList getCowLevel = new ArrayList();
        for (Object key : stages.keySet()) {

            list.add(key);
             String s = (String) key;
            if (s.contains("spst")){
                getCowLevel.add(s);
            }
//            System.out.println(list);
//            System.out.println(s);
//            System.out.println("Key = " + key);
        }

        System.out.println(getCowLevel);


    }

    @Test
    void test() throws IOException {
        //load config
        String loadConfig = FileUtils.readFileToString(new File("src/main/resources/data/config/config.json"), "utf-8");
        String loadChars = FileUtils.readFileToString(new File("src/main/resources/data/excel/character_table.json"), "utf-8");

        ArrayList listChar = new ArrayList();
        ArrayList listInstId = new ArrayList();
        int maxInstId = 0;

        Map<String, Object> mapConfig = JsonUtils.transferToMap(loadConfig);



        Map<String, Object> mapChar = JsonUtils.transferToMap(loadChars);
        Set<String> keySet = mapChar.keySet();
        for (String key : keySet) {
            if (key.contains("char")) {
                listChar.add(key);
                String charInstId = StringUtils.substringBetween(key, "_");
                listInstId.add(charInstId);
                int tempInstId = Integer.parseInt(charInstId);
                 if (tempInstId > maxInstId){
                     maxInstId = tempInstId;
                 }
            }
        }
//        System.out.println(listChar);
        System.out.println(listChar.size());
        System.out.println(listInstId.size());
        System.out.println(maxInstId);
    }

    @Test
    void testConfig() throws IOException {
        String loadConfig = FileUtils.readFileToString(new File("src/main/resources/data/config/config.json"), "utf-8");
        Map<String, Object> mapConfig = JsonUtils.transferToMap(loadConfig);
        Map charConfig = (Map) mapConfig.get("charConfig");
        System.out.println(charConfig);
    }

}

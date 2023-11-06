package com.openarknightsjvav;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.map.TableMap;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

        ArrayList<String> listChar = new ArrayList<>();
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
        for (int i = 0; i < listChar.size(); i++) {
            boolean b = StringUtils.containsAny(listChar.get(i), "char_508_aguard", "char_509_acast", "char_510_amedic", "char_511_asnipe");
            System.out.println(b);

        }
//        System.out.println(listChar);
        System.out.println(listChar.size());
        System.out.println(listInstId.size());
        System.out.println(maxInstId);
    }

    @Test
    void testConfig() throws IOException {
        String loadActivity = FileUtils.readFileToString(new File("src/main/resources/data/excel/activity_table.json"), "utf-8");
        Map Activity = JsonUtils.getValue(loadActivity, "activity.TYPE_ACT17SIDE.act17side", Map.class);
        Map<String, Object> archiveItemUnlockDataMap = (Map) Activity.get("archiveItemUnlockDataMap");
        ArrayList<Object> listChapterId = new ArrayList<>();
        ArrayList<Object> listItemId = new ArrayList<>();
        HashMap<String, Object> mapChapter = new HashMap<>();
        List list = new ArrayList<>();
        for (String key : archiveItemUnlockDataMap.keySet()) {
            if (key.contains("log")){
                String chapterId = (String)((Map) archiveItemUnlockDataMap.get(key)).get("chapterId");
                String itemId = (String)((Map) archiveItemUnlockDataMap.get(key)).get("itemId");
                mapChapter.put(key,Map.of("chapterId",chapterId,"itemId",itemId));
                list.add(MapUtil.of(chapterId,itemId));
            }
        }
        Map listMap = MapUtil.toListMap(list);
        System.out.println(listMap);

    }

}

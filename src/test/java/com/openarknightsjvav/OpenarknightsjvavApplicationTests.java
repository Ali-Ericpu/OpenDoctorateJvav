package com.openarknightsjvav;

import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
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
        String s = FileUtils.readFileToString(new File("src/main/resources/data/excel/story_table.json"));
        Map<String, Object> flagsMap = JsonUtils.transferToMap(s);
        for (int i = 0; i < flagsMap.size(); i++) {
            Set<String> set = flagsMap.keySet();
            System.out.println(set);
        }
    }



}

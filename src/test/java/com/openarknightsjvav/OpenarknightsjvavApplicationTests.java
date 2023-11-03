package com.openarknightsjvav;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.google.gson.stream.JsonReader;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class OpenarknightsjvavApplicationTests {

    @Test
        public void getStages() throws IOException {
        String stage = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"));
        Map<String, Object> map = JsonUtils.transferToMap(stage);
//        System.out.println(map);
        Map stages = (Map) map.get("stages");
//        System.out.println(stages);


        for (Object key : stages.keySet()) {
            ArrayList list = new ArrayList();
            list.add(key);
            System.out.println(list);
//            System.out.println("Key = " + key);

        }

    }


}

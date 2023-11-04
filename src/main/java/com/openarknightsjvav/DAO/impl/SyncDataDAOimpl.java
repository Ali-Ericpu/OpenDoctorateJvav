package com.openarknightsjvav.DAO.impl;

import com.openarknightsjvav.DAO.SyncDataDAO;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SyncDataDAOimpl
 * Package: com.openarknightsjvav.DAO.impl
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/3 13:25
 * @Version 1.0
 */
@Repository
public class SyncDataDAOimpl implements SyncDataDAO {

    @Override
    public List getStages() throws IOException {
        //读取json转为string
        String stage = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"));
        //调用工具类将读取到的字符串转化为map
        Map<String, Object> map = JsonUtils.transferToMap(stage);
        //获取key为stages的value并重新封装为一个map
        Map stages = (Map) map.get("stages");
        //遍历stages的所有key并封装到list中
        ArrayList list = new ArrayList();
        for (Object key : stages.keySet()) {
            list.add(key);
//            System.out.println(list);

        }
        return list;
    }
}

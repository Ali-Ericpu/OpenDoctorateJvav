package com.openarknightsjvav.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.openarknightsjvav.DAO.impl.SyncDataDAOimpl;
import com.openarknightsjvav.pojo.syncData.Dungeon;
import com.openarknightsjvav.service.SyncDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ClassName, SyncDataServiceImpl
 * Package, com.cryrain.openarknightsjava.service.impl
 * Description,
 *
 * @author Raincc
 * @Create 2023/11/2 19,57
 * @Version 1.0
 */
@Service
public class SyncDataServiceImpl implements SyncDataService {

    @Autowired
    private SyncDataDAOimpl syncDataDAOimpl;

    @Override
    public LinkedHashMap getSyncData() throws IOException {

        LinkedHashMap syncData = new LinkedHashMap();
        //dungoen
        Dungeon dungeon = new Dungeon();
        //dungeon//stages
        LinkedHashMap putStages = new LinkedHashMap();
        List stages = syncDataDAOimpl.getStages();
        //使用DAO获取的list给stages赋值
        for (int i = 0; i < stages.size(); i++) {
            LinkedHashMap innerStages = new LinkedHashMap();
            innerStages.put("completeTimes", 1);
            innerStages.put("hasBattleReplay", 0);
            innerStages.put("noCostCnt", 0);
            innerStages.put("practiceTimes", 0);
            innerStages.put("stageId", stages.get(i));
            innerStages.put("startTimes", 1);
            innerStages.put("state", 3);
            putStages.put(stages.get(i), innerStages);
            dungeon.setStages(putStages);
        }
        //dungeon//cowLevel
        dungeon.setCowLevel(new LinkedHashMap());
        syncData.put("dungeon", BeanUtil.beanToMap(dungeon));


        return syncData;
    }
}

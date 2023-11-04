package com.openarknightsjvav.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.openarknightsjvav.DAO.impl.SyncDataDAOimpl;
import com.openarknightsjvav.service.SyncDataService;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        syncData.put("dungeon", syncDataDAOimpl.getDungeon());

        //activity
        syncData.put("activity",syncDataDAOimpl.getActivity());

        //status
        syncData.put("status",syncDataDAOimpl.getStatus());

        //troop
        syncData.put("troop",syncDataDAOimpl.getTroop());


        return syncData;
    }
}

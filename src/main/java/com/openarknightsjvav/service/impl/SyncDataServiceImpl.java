package com.openarknightsjvav.service.impl;

import cn.hutool.db.ActiveEntity;
import com.openarknightsjvav.DAO.impl.SyncDataDAOimpl;
import com.openarknightsjvav.service.SyncDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        syncData.put("activity", syncDataDAOimpl.getActivity());

        //status
        syncData.put("status", syncDataDAOimpl.getStatus());

        //troop
        syncData.put("troop", syncDataDAOimpl.getTroop());

        //npcAudio
        syncData.put("npcAudio", syncDataDAOimpl.getNpcAudio());

        //pushFlags
        syncData.put("pushFlags", syncDataDAOimpl.getPushFlags());

        //equipment
        syncData.put("equipment", new HashMap<>());

        //skin
        syncData.put("skin", syncDataDAOimpl.getSkin());

        //mission
        syncData.put("mission", syncDataDAOimpl.getMission());

        //dexNav
        syncData.put("dexNav", syncDataDAOimpl.getDexNav());

        //crisis
        syncData.put("crisis", Map.of("lst", 1699178812, "nst", 1699182412));

        //backflow
        syncData.put("backflow", syncDataDAOimpl.getBackFlow());

        //mainline
        syncData.put("mainline", Map.of("record", new HashMap<>(), "cache", new ArrayList<>(), "version", 1));

        //avatar
        syncData.put("avatar", syncDataDAOimpl.getAvatar());




        return syncData;
    }
}

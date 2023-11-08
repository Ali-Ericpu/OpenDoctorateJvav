package com.openarknightsjvav.service;

import com.google.gson.Gson;
import com.openarknightsjvav.DAO.SyncDataDAO;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
public class SyncDataService {

    @Autowired
    private SyncDataDAO syncDataDAO;



    public LinkedHashMap getSyncData() throws IOException {

        File file = new File("src/main/resources/syncData.json");

//        if (file.exists()){
//            String loadSynvData = FileUtils.readFileToString(file);
//            return (LinkedHashMap) JsonUtils.transferToMap(loadSynvData);
//        }

        LinkedHashMap<Object, Object> result = new LinkedHashMap<>();
        LinkedHashMap syncData = new LinkedHashMap();

        //dungoen
        syncData.put("dungeon", syncDataDAO.getDungeon());

        //activity
        syncData.put("activity", syncDataDAO.getActivity());

        //status
        syncData.put("status", syncDataDAO.getStatus());

        //troop
        syncData.put("troop", syncDataDAO.getTroop());

        //npcAudio
        syncData.put("npcAudio", syncDataDAO.getNpcAudio());

        //pushFlags
        syncData.put("pushFlags", syncDataDAO.getPushFlags());

        //equipment
        syncData.put("equipment", new HashMap<>());

        //skin
        syncData.put("skin", syncDataDAO.getSkin());

        //mission
        syncData.put("mission", syncDataDAO.getMission());

        //dexNav
        syncData.put("dexNav", syncDataDAO.getDexNav());

        //crisis
        syncData.put("crisis", Map.of("lst", 1699178812, "nst", 1699364460));

        //backflow
        syncData.put("backflow", syncDataDAO.getBackFlow());

        //mainline
        syncData.put("mainline", Map.of("record", new HashMap<>(), "cache", new ArrayList<>(), "version", 1));

        //avatar
        syncData.put("avatar", syncDataDAO.getAvatarAndBackground().get(0));

        //background
        syncData.put("background", syncDataDAO.getAvatarAndBackground().get(1));

        //medal
        syncData.put("medal", new HashMap<>());

        //homeTheme
        syncData.put("homeTheme", syncDataDAO.getAvatarAndBackground().get(2));

        //rlv2
        syncData.put("rlv2", syncDataDAO.getRlv2());

        //deepSea
        syncData.put("deepSea", syncDataDAO.getDeepSea());

        //rlv2
        syncData.put("tower", syncDataDAO.getTower());

        //siracusaMap
        syncData.put("siracusaMap", syncDataDAO.getSiracusaMap());

        //storyreview
        syncData.put("storyreview", new HashMap<>());

        //setting
        syncData.put("setting", new HashMap<>());

        //openServer
        syncData.put("openServer", new HashMap<>());

        //aprilFool
        syncData.put("aprilFool", new HashMap<>());

        //tshop
        syncData.put("tshop", new HashMap<>());

        //retro
        syncData.put("retro", syncDataDAO.getRetro());

        //roguelike
        syncData.put("roguelike", syncDataDAO.getRoguelike());

        //carousel
        syncData.put("carousel", new HashMap<>());

        //event
        syncData.put("event", Map.of("building", 0, "status", 0));

        //collectionReward
        syncData.put("collectionReward", new HashMap<>());

        //campaignsV2
        syncData.put("campaignsV2", syncDataDAO.getCampaignsV2());

        //inventory
        syncData.put("inventory", new HashMap<>());

        //recruit
        syncData.put("recruit", new HashMap<>());

        //consumable
        syncData.put("consumable", new HashMap<>());

        //checkIn
        syncData.put("checkIn", syncDataDAO.getCheckIn());

        //charm
        syncData.put("charm", syncDataDAO.getCharm());

        //ticket
        syncData.put("ticket", new HashMap<>());

        //car
        syncData.put("car", syncDataDAO.getCar());

        Gson gson = new Gson();

        FileUtils.writeStringToFile(new File("data/syncData.json"), gson.toJson(syncData), "utf-8");

        result.put("playerDataDelta", Map.of("modified", new HashMap<>(), "deleted", new HashMap<>()));
        result.put("result", 0);
        result.put("ts", System.currentTimeMillis() / 1000);
        result.put("user", syncData);

        return result;
    }
}

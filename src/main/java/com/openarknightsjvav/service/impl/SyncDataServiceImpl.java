package com.openarknightsjvav.service.impl;

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
        syncData.put("crisis", Map.of("lst", 1699178812, "nst", 1699364460));

        //backflow
        syncData.put("backflow", syncDataDAOimpl.getBackFlow());

        //mainline
        syncData.put("mainline", Map.of("record", new HashMap<>(), "cache", new ArrayList<>(), "version", 1));

        //avatar
        syncData.put("avatar", syncDataDAOimpl.getAvatarAndBackground().get(0));

        //background
        syncData.put("background", syncDataDAOimpl.getAvatarAndBackground().get(1));

        //medal
        syncData.put("medal", new HashMap<>());

        //homeTheme
        syncData.put("homeTheme", syncDataDAOimpl.getAvatarAndBackground().get(2));

        //rlv2
        syncData.put("rlv2", syncDataDAOimpl.getRlv2());

        //deepSea
        syncData.put("deepSea", syncDataDAOimpl.getDeepSea());

        //rlv2
        syncData.put("tower", syncDataDAOimpl.getTower());

        //siracusaMap
        syncData.put("siracusaMap", syncDataDAOimpl.getSiracusaMap());

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
        syncData.put("retro", syncDataDAOimpl.getRetro());

        //roguelike
        syncData.put("roguelike", syncDataDAOimpl.getRoguelike());

        //carousel
        syncData.put("carousel", new HashMap<>());

        //event
        syncData.put("event", Map.of("building", 0, "status", 0));

        //collectionReward
        syncData.put("collectionReward", new HashMap<>());

        //campaignsV2
        syncData.put("campaignsV2", syncDataDAOimpl.getCampaignsV2());

        //inventory
        syncData.put("inventory", new HashMap<>());

        //recruit
        syncData.put("recruit", new HashMap<>());

        //consumable
        syncData.put("consumable", new HashMap<>());

        //checkIn
        syncData.put("checkIn", syncDataDAOimpl.getCheckIn());

        //charm
        syncData.put("charm", syncDataDAOimpl.getCharm());

        //ticket
        syncData.put("ticket", new HashMap<>());

        //car
        syncData.put("car", syncDataDAOimpl.getCar());

        return syncData;
    }
}

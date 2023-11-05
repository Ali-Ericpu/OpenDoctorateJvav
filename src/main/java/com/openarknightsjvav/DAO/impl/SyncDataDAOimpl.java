package com.openarknightsjvav.DAO.impl;

import com.openarknightsjvav.DAO.SyncDataDAO;
import com.openarknightsjvav.utils.Confing;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    private Long timeStamp = System.currentTimeMillis() / 1000;

    @Override
    public LinkedHashMap getDungeon() throws IOException {

        LinkedHashMap dungeon = new LinkedHashMap();
        ArrayList listCowLevel = new ArrayList();
        //读取json转为string
        String stage = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"), "utf-8");
        //调用工具类将读取到的字符串转化为map
        Map<String, Object> map = JsonUtils.transferToMap(stage);
        //获取key为stages的value并重新封装为一个map
        Map stagesKey = (Map) map.get("stages");
        //遍历stages的所有key并封装到list中
        ArrayList listStages = new ArrayList();
        for (Object key : stagesKey.keySet()) {
            listStages.add(key);
            String s = (String) key;
            if (s.contains("spst")) {
                listCowLevel.add(s);
            }
//            System.out.println(list);

        }
        LinkedHashMap putStages = new LinkedHashMap();
        LinkedHashMap stages = new LinkedHashMap();
        for (int i = 0; i < listStages.size(); i++) {
            LinkedHashMap innerStages = new LinkedHashMap();
            innerStages.put("completeTimes", 1);
            innerStages.put("hasBattleReplay", 0);
            innerStages.put("noCostCnt", 0);
            innerStages.put("practiceTimes", 0);
            innerStages.put("stageId", listStages.get(i));
            innerStages.put("startTimes", 1);
            innerStages.put("state", 3);
            putStages.put(listStages.get(i), innerStages);
            stages.putAll(putStages);

        }
        dungeon.put("stages", stages);

        LinkedHashMap putCowLevel = new LinkedHashMap();
        LinkedHashMap cowlevel = new LinkedHashMap();
        for (int i = 0; i < listCowLevel.size(); i++) {
            LinkedHashMap innerCowLevel = new LinkedHashMap();
            innerCowLevel.put("id", listCowLevel.get(i));
            innerCowLevel.put("type", "STAGE");
            innerCowLevel.put("var", new Boolean[]{true});
            innerCowLevel.put("fts", timeStamp - 100 + i);
            innerCowLevel.put("rts", timeStamp - 50 + i);
            putCowLevel.put(listCowLevel.get(i), innerCowLevel);
            cowlevel.putAll(putCowLevel);
        }
        dungeon.put("cowLevel", cowlevel);
        return dungeon;
    }

    @Override
    public LinkedHashMap getActivity() {
        //遍历activity_table.json，使用ognl表达式获取第一个出现的type属性contains SIDE的id和type值
        return new LinkedHashMap();
    }

    @Override
    public LinkedHashMap getStatus() throws IOException {
        LinkedHashMap status = new LinkedHashMap();
        LinkedHashMap flags = new LinkedHashMap();
        //config
        status.put("nickName", "Doctorate");

        status.put("nickNumber", "1111");
        status.put("level", 120);
        status.put("exp", "0");
        status.put("socialPoint", 6);
        status.put("gachaTickt", 0);
        status.put("tenGachaTicket", 0);
        status.put("instantFinishTicket", 0);
        status.put("hggShard", 0);
        status.put("lggShard", 0);
        status.put("recruitLicense", 0);
        status.put("progress", 30000);
        status.put("buyApRemainTimes", 99);
        status.put("apLimitUpFlag", 0);
        status.put("uid", "1111");

        flags.put("init", 1);
        String s = FileUtils.readFileToString(new File("src/main/resources/data/excel/story_table.json"), "utf-8");
        Map<String, Object> flagsMap = JsonUtils.transferToMap(s);
        Set<String> keySet = flagsMap.keySet();
        for (String key : keySet) {
            flags.put(key, 1);
        }
        status.put("flags", flags);

        status.put("ap", 130);
        status.put("maxAp", 130);
        status.put("androidDiamond", 0);
        status.put("iosDiamond", 0);
        status.put("diamondShard", 0);
        status.put("gold", 0);
        status.put("practiceTicket", 0);
        status.put("lastRefreshTs", timeStamp);
        status.put("lastApAddTime", timeStamp);
        status.put("mainStageProgress", null);
        status.put("registerTs", timeStamp);
        status.put("lastOnlineTs", timeStamp);
        status.put("serverName", "Doctorate");
        status.put("avatarId", 0);
        status.put("resume", "DoctorateJvav");
        status.put("friendNumLimit", 999);
        status.put("monthlySubscriptionStartTime", 0);
        status.put("monthlySubscriptionEndTime", 0);
        //config
        status.put("secretary", "char_249_mlyss");
        status.put("secretarySkinId", "char_249_mlyss#2");

        status.put("tipMonthlyCardExpireTs", 0);
        //config
        LinkedHashMap avatar = new LinkedHashMap();
        avatar.put("type", "ICON");
        avatar.put("id", "avatar_activity_RI");
        status.put("avatar", avatar);

        //config
        status.put("globalVoiceLan", "JP");

        return status;
    }

    @Override
    public LinkedHashMap getTroop() throws IOException {
        LinkedHashMap troop = new LinkedHashMap();
        String loadChars = FileUtils.readFileToString(new File("src/main/resources/data/excel/character_table.json"), "utf-8");
        String loadSkins = FileUtils.readFileToString(new File("src/main/resources/data/excel/skin_table.json"), "utf-8");
        String loadEquip = FileUtils.readFileToString(new File("src/main/resources/data/excel/uniequip_table.json"), "utf-8");

        //chars
        Integer maxInstId = 0;        //curCharInstId
        ArrayList<Integer> defaultSkillIndex = new ArrayList();     //skillIndex
        ArrayList<Integer> phases = new ArrayList<>();         //phases
        Map<String, Double> charConfig = Confing.getCharConfig();
        ArrayList<String> listChars = new ArrayList<>();         //char
        ArrayList<Integer> listInstId = new ArrayList<>();      //instId
        ArrayList<String> skins = new ArrayList<>();            //skin
        ArrayList<String> currentEquip = new ArrayList<>();     //currentEquip
        LinkedHashMap<String, LinkedHashMap> equip = new LinkedHashMap<>();     //equip
        LinkedHashMap<Integer, Object> chars = new LinkedHashMap<>();
        ArrayList<Integer> skillCount;
        ArrayList<Object> evolvePhase;
        ArrayList<Double> level = new ArrayList<>();
        ArrayList<ArrayList> listLevel = new ArrayList<>();
        ArrayList<ArrayList> listSkills = new ArrayList<>();
        ArrayList<Object> skills = new ArrayList<>();


        Map<String, Object> mapChar = JsonUtils.transferToMap(loadChars);
        Set<String> setChar = mapChar.keySet();
        for (String key : setChar) {
            if (key.contains("char")) {
                listChars.add(key);
                //将substring处理过后的key放入list
                int tempInstId = Integer.parseInt(StringUtils.substringBetween(key, "_"));
                listInstId.add(tempInstId);
                if (tempInstId > maxInstId) {
                    maxInstId = tempInstId + 1;
                }
            }
        }
        for (int i = 0; i < listChars.size(); i++) {
            Map mapChars = (Map) mapChar.get(listChars.get(i));
            //skillindex
            skillCount = (ArrayList) mapChars.get("skills");
            listSkills.add(i, skillCount);
            defaultSkillIndex.add(skillCount.size());
            //evolvePhase
            evolvePhase = (ArrayList) mapChars.get("phases");
            listLevel.add(i, evolvePhase);
            phases.add(evolvePhase.size());

//            System.out.println(defaultSkillIndex);
        }
        //evolvePhase
        if (charConfig.get("evolvePhase").intValue() == 0) {
            for (int i = 0; i < phases.size(); i++) {
                phases.set(i, 1);
            }
        } else if (charConfig.get("evolvePhase").intValue() == 1) {
            int i = 0;
            while (phases.get(i) == 3 && i < phases.size()) {
                phases.set(i, 2);
                i++;
            }
        }

        //level
        if (charConfig.get("level").intValue() == -1) {
            for (int i = 0; i < listChars.size(); i++) {
                Map mapLevel = (Map) (listLevel.get(i).get(phases.get(i) - 1));
                level.add((Double) mapLevel.get("maxLevel"));
            }
        } else {
            //又不是不能用0.0
            for (int i = 0; i < listChars.size(); i++) {
                level.add(charConfig.get("level"));
            }
        }
        //skills
        for (int i = 0; i < listChars.size(); i++) {
            LinkedHashMap<String, Object> skillId = new LinkedHashMap();
            //E2
            if ((listSkills.get(i)).size() >= 2) {
                ArrayList<Object> list = new ArrayList<>();
                for (int j = 0; j < (listSkills.get(i)).size(); j++) {
                    Map map = (Map) (listSkills.get(i).get(j));
                    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("skillId", map.get("skillId"));
                    linkedHashMap.put("unlock", 1);
                    linkedHashMap.put("state", 0);
                    linkedHashMap.put("specializeLevel", charConfig.get("skillsSpecializeLevel").intValue());
                    linkedHashMap.put("completeUpgradeTime", -1);
                    list.add(linkedHashMap);
                }
                skills.add(list);
                //E1
            } else if ((listSkills.get(i)).size() == 1) {
                Map map = (Map) (listSkills.get(i).get(0));
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("skillId", map.get("skillId"));
                linkedHashMap.put("unlock", 1);
                linkedHashMap.put("state", 0);
                linkedHashMap.put("specializeLevel", 0);
                linkedHashMap.put("completeUpgradeTime", -1);
                skills.add(new LinkedHashMap<>().put(i, linkedHashMap));
                //E0
            } else if ((listSkills.get(i)).size() == 0) {
                skills.add(new ArrayList<>());
            }
        }
        //get all skin
        Map<String, Object> mapCharSkin = JsonUtils.transferToMap(loadSkins);
        Map<String, Map> mapSkins = (Map) mapCharSkin.get("charSkins");
        HashMap<String, Map> tempSkin = new HashMap<>();
        ArrayList<String> listSkinId = new ArrayList<>();
        for (String s : mapSkins.keySet()) {
            listSkinId.add(s);
        }
        for (int i = 0; i < mapSkins.size(); i++) {
            String s0 = (String) (mapSkins.get(listSkinId.get(i))).get("skinId");
            if (s0.contains("@")) {
                String s1 = (String) (mapSkins.get(listSkinId.get(i))).get("charId");
                tempSkin.put(s1, Map.of("skinId", s0,
                        "charId", s1));
            }
        }
        //skins
        for (int i = 0; i < listChars.size(); i++) {
            //E0&E1  expect roguelike char
            if ((listSkills.get(i)).size() <= 1 || StringUtils.containsAny(listChars.get(i), "char_508_aguard", "char_509_acast", "char_510_amedic", "char_511_asnipe")) {
                skins.add(listChars.get(i) + "#1");
                //E2
            } else if ((listSkills.get(i)).size() > 1) {
                skins.add(listChars.get(i) + "#2");
            }
            //seasonal
            if (tempSkin.get(listChars.get(i)) != null &&
                    (tempSkin.get(listChars.get(i))).get("charId").equals(listChars.get(i))) {
                skins.set(i, (String) (tempSkin.get(listChars.get(i))).get("skinId"));
            }
        }
        //currentEquip and equip
        HashMap<String, Map> listEquip = new HashMap();
        Map<String, Object> mapEquip = JsonUtils.transferToMap(loadEquip);
        Map<String, ArrayList> CharEquip = (Map) mapEquip.get("charEquip");
        Map<Object, Object> mapAllEquip = new HashMap<>();
        for (String equipKey : CharEquip.keySet()) {
            listEquip.put(equipKey, Map.of("charId", equipKey,
                    "equipId", CharEquip.get(equipKey).get(CharEquip.get(equipKey).size() - 1),
                    "listEquip", CharEquip.get(equipKey)));
        }
        for (int i = 0; i < listChars.size(); i++) {
            currentEquip.add("null");
            if (listEquip.get(listChars.get(i)) == null) {
                currentEquip.set(i, null);
                equip.put(listChars.get(i), new LinkedHashMap());
            } else if (listEquip.get(listChars.get(i)).get("charId").equals(listChars.get(i))) {
                currentEquip.set(i, (String) listEquip.get(listChars.get(i)).get("equipId"));
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                for (int j = 0; j < ((ArrayList) listEquip.get(listChars.get(i)).get("listEquip")).size(); j++) {
                    HashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("hide", 0);
                    linkedHashMap.put("locked", 0);
                    linkedHashMap.put("level", j > 0 ? 3 : 1);    //又不是不能用0.0
                    hashMap.put(((ArrayList<?>) listEquip.get(listChars.get(i)).get("listEquip")).get(j), linkedHashMap);
                }
                equip.put(listChars.get(i), hashMap);
            }
        }

        for (int i = 0; i < listChars.size(); i++) {
            LinkedHashMap<String, Object> innerChars = new LinkedHashMap<>();

            innerChars.put("instId", listInstId.get(i));
            innerChars.put("charId", listChars.get(i));
            innerChars.put("favorPoint", charConfig.get("favorPoint").intValue());
            innerChars.put("potentialRank", charConfig.get("potentialRank").intValue());
            innerChars.put("mainSkillLvl", charConfig.get("mainSkillLvl").intValue());
            innerChars.put("skin", skins.get(i));
            innerChars.put("level", level.get(i).intValue());
            innerChars.put("exp", 0);
            innerChars.put("evolvePhase", phases.get(i) - 1);

            //amiya
            if (listChars.get(i).equals("char_002_amiya")) {

                LinkedHashMap<String, Object> amiya = new LinkedHashMap<>();
                LinkedHashMap<String, Object> amiya2 = new LinkedHashMap<>();
                LinkedHashMap<Object, Object> tmpl = new LinkedHashMap<>();
                ArrayList<LinkedHashMap> amiyaSkills = new ArrayList<>();
                String[] skillName= {"skchr_amiya2_1","skchr_amiya2_2"};

                amiya.put("skinId", "char_002_amiya@winter#1");
                amiya.put("defaultSkillIndex", 2);
                amiya.put("skills", skills.get(i));
                amiya.put("currentEquip", currentEquip.get(i));
                amiya.put("equip", equip.get(listChars.get(i)));
                amiya2.put("skinId", "char_1001_amiya2@casc#1");
                amiya2.put("defaultSkillIndex", 1);
                for (int j = 0; j <= 1; j++) {
                    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("skillId", skillName[j]);
                    linkedHashMap.put("unlock", 1);
                    linkedHashMap.put("state", 0);
                    linkedHashMap.put("specializeLevel",charConfig.get("skillsSpecializeLevel").intValue());
                    linkedHashMap.put("completeUpgradeTime", -1);
                    amiyaSkills.add(linkedHashMap);
                }
                amiya2.put("skills", amiyaSkills);
                amiya2.put("currentEquip", null);
                amiya2.put("equip", new HashMap<>());
                tmpl.put("char_002_amiya", amiya);
                tmpl.put("char_1001_amiya2", amiya2);

                innerChars.put("defaultSkillIndex", -1);
                innerChars.put("gainTime", timeStamp);
                innerChars.put("skills", new ArrayList<>());
                innerChars.put("voiceLan", "JP");
                innerChars.put("currentEquip", currentEquip.get(i));
                innerChars.put("equip", equip.get(listChars.get(i)));
                innerChars.put("starMark", 0);
                innerChars.put("currentTmpl", "char_002_amiya");
                innerChars.put("tmpl", tmpl);

                chars.put(listInstId.get(i), innerChars);

                continue;
            }

            innerChars.put("defaultSkillIndex", defaultSkillIndex.get(i) - 1);
            innerChars.put("gainTime", timeStamp);
            innerChars.put("skills", skills.get(i));
            innerChars.put("voiceLan", "JP");
            innerChars.put("currentEquip", currentEquip.get(i));
            innerChars.put("equip", equip.get(listChars.get(i)));
            innerChars.put("starMark", 0);

            chars.put(listInstId.get(i), innerChars);
        }

        //charGroup
        LinkedHashMap<String, Object> charGroup = new LinkedHashMap<>();
        for (int i = 0; i < listChars.size(); i++) {
            charGroup.put(listChars.get(i), Map.of("favorPoint", 25570));
        }


        troop.put("curCharInstId", maxInstId);
        troop.put("curSquadCount", 4);

        //config
        String loadSquads = FileUtils.readFileToString(new File("src/main/resources/data/config/squads.json"), "utf-8");
        Map<String, Object> squadsMap = JsonUtils.transferToMap(loadSquads);
        troop.put("squads", squadsMap);

        troop.put("chars", chars);

        troop.put("charGroup", charGroup);
        troop.put("charMission", new HashMap<>());
        troop.put("addon", 4);

        return troop;
    }

}

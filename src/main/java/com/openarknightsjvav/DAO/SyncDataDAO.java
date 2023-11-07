package com.openarknightsjvav.DAO;

import cn.hutool.core.map.MapUtil;
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
public class SyncDataDAO {

    private Long timeStamp = System.currentTimeMillis() / 1000;

    public LinkedHashMap getDungeon() throws IOException {

        LinkedHashMap dungeon = new LinkedHashMap();
        ArrayList listCowLevel = new ArrayList();
        String stage = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"), "utf-8");
        Map<String, Object> map = JsonUtils.transferToMap(stage);
        Map stagesKey = (Map) map.get("stages");
        ArrayList listStages = new ArrayList();
        for (Object key : stagesKey.keySet()) {
            listStages.add(key);
            String s = (String) key;
            if (s.contains("spst")) {
                listCowLevel.add(s);
            }
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

    public LinkedHashMap getActivity() {
        //遍历activity_table.json，使用ognl表达式获取第一个出现的type属性contains SIDE的id和type值
        return new LinkedHashMap();
    }

    public LinkedHashMap getStatus() throws IOException {
        LinkedHashMap<String, Object> status = new LinkedHashMap();
        LinkedHashMap flags = new LinkedHashMap();
        //flags
        flags.put("init", 1);
        String s = FileUtils.readFileToString(new File("src/main/resources/data/excel/story_table.json"), "utf-8");
        Map<String, Object> flagsMap = JsonUtils.transferToMap(s);
        Set<String> keySet = flagsMap.keySet();
        for (String key : keySet) {
            flags.put(key, 1);
        }
        //avatar
        LinkedHashMap avatar = new LinkedHashMap();
        avatar.put("type", "ICON");
        avatar.put("id", "avatar_activity_RI");
        //secretary
        String secretary =(String) Confing.getUserConfig().get("secretary");
        String secretarySkinId =(String) Confing.getUserConfig().get("secretarySkinId");

        status.put("nickName", "Doctorate");//config
        status.put("nickNumber", "1111");
        status.put("level", 120);
        status.put("exp", 0);
        status.put("socialPoint", 6);
        status.put("gachaTickt", 0);
        status.put("tenGachaTicket", 1);
        status.put("instantFinishTicket", 0);
        status.put("hggShard", 0);
        status.put("lggShard", 0);
        status.put("recruitLicense", 0);
        status.put("progress", 30000);
        status.put("buyApRemainTimes", 99);
        status.put("apLimitUpFlag", 0);
        status.put("uid", "1111");
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
        status.put("avatarId", "0");
        status.put("resume", "DoctorateJvav");
        status.put("friendNumLimit", 999);
        status.put("monthlySubscriptionStartTime", 0);
        status.put("monthlySubscriptionEndTime", 0);
        status.put("secretary", secretary);
        status.put("secretarySkinId", secretarySkinId);
        status.put("tipMonthlyCardExpireTs", 0);
        status.put("avatar", avatar);       //config
        status.put("globalVoiceLan", "JP");     //config

        return status;
    }

    public LinkedHashMap getTroop() throws IOException {
        LinkedHashMap troop = new LinkedHashMap();
        String loadChars = FileUtils.readFileToString(new File("src/main/resources/data/excel/character_table.json"), "utf-8");
        String loadSkins = FileUtils.readFileToString(new File("src/main/resources/data/excel/skin_table.json"), "utf-8");
        String loadEquip = FileUtils.readFileToString(new File("src/main/resources/data/excel/uniequip_table.json"), "utf-8");
        String loadAddon = FileUtils.readFileToString(new File("src/main/resources/data/excel/handbook_info_table.json"), "utf-8");

        //chars
        Integer maxInstId = 0;        //curCharInstId
        ArrayList<Integer> defaultSkillIndex = new ArrayList();     //skillIndex
        ArrayList<Integer> phases = new ArrayList<>();         //phases
        Map<String, Object> charConfig = Confing.getCharConfig();
        ArrayList<String> listChars = new ArrayList<>();         //char
        ArrayList<Integer> listInstId = new ArrayList<>();      //instId
        ArrayList<String> skins = new ArrayList<>();            //skin
        ArrayList<String> currentEquip = new ArrayList<>();     //currentEquip
        LinkedHashMap<String, LinkedHashMap> equip = new LinkedHashMap<>();     //equip
        LinkedHashMap<Integer, Object> chars = new LinkedHashMap<>();   //chars
        HashMap<Object, Object> addon = new HashMap<>();
        ArrayList<Integer> skillCount;
        ArrayList<Object> evolvePhase;
        ArrayList level = new ArrayList<>();
        ArrayList<ArrayList> listLevel = new ArrayList<>();
        ArrayList<ArrayList> listSkills = new ArrayList<>();
        ArrayList<Object> skills = new ArrayList<>();

        Map<String, Object> mapChar = JsonUtils.transferToMap(loadChars);
        for (String key : mapChar.keySet()) {
            if (key.contains("char")) {
                listChars.add(key);
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
        if ((Long) charConfig.get("evolvePhase") == 0) {
            for (int i = 0; i < phases.size(); i++) {
                phases.set(i, 1);
            }
        } else if ((Long)charConfig.get("evolvePhase") == 1) {
            int i = 0;
            while (phases.get(i) == 3 && i < phases.size()) {
                phases.set(i, 2);
                i++;
            }
        }

        //level
        if ((Long)charConfig.get("level") == -1) {
            for (int i = 0; i < listChars.size(); i++) {
                Map mapLevel = (Map) (listLevel.get(i).get(phases.get(i) - 1));
                level.add( mapLevel.get("maxLevel"));
            }
        } else {
            //又不是不能用0.0
            for (int i = 0; i < listChars.size(); i++) {
                level.add(charConfig.get("level"));
            }
        }
        //skills
        for (int i = 0; i < listChars.size(); i++) {
            //E2
            if ((listSkills.get(i)).size() >= 2) {
                ArrayList<Object> list = new ArrayList<>();
                for (int j = 0; j < (listSkills.get(i)).size(); j++) {
                    Map map = (Map) (listSkills.get(i).get(j));
                    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("skillId", map.get("skillId"));
                    linkedHashMap.put("unlock", 1);
                    linkedHashMap.put("state", 0);
                    linkedHashMap.put("specializeLevel", charConfig.get("skillsSpecializeLevel"));
                    linkedHashMap.put("completeUpgradeTime", -1);
                    list.add(linkedHashMap);
                }
                skills.add(list);
                //Roguelike chars
            } else if ((listSkills.get(i)).size() == 1 &&
                    StringUtils.containsAny(listChars.get(i), "char_508_aguard", "char_509_acast", "char_510_amedic", "char_511_asnipe")) {
                Map map = (Map) (listSkills.get(i).get(0));
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("skillId", map.get("skillId"));
                linkedHashMap.put("unlock", 1);
                linkedHashMap.put("state", 0);
                linkedHashMap.put("specializeLevel", charConfig.get("skillsSpecializeLevel"));
                linkedHashMap.put("completeUpgradeTime", -1);
                skills.add(i, List.of(linkedHashMap));
                //E1
            } else if ((listSkills.get(i)).size() == 1) {
                Map map = (Map) (listSkills.get(i).get(0));
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("skillId", map.get("skillId"));
                linkedHashMap.put("unlock", 1);
                linkedHashMap.put("state", 0);
                linkedHashMap.put("specializeLevel", 0);
                linkedHashMap.put("completeUpgradeTime", -1);
                skills.add(i, List.of(linkedHashMap));
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
                    linkedHashMap.put("level", j > 0 ? 3 : 1);    //又不是不能用0.0   maybe config
                    hashMap.put(((ArrayList<?>) listEquip.get(listChars.get(i)).get("listEquip")).get(j), linkedHashMap);
                }
                equip.put(listChars.get(i), hashMap);
            }
        }

        for (int i = 0; i < listChars.size(); i++) {
            LinkedHashMap<String, Object> innerChars = new LinkedHashMap<>();

            innerChars.put("instId", listInstId.get(i));
            innerChars.put("charId", listChars.get(i));
            innerChars.put("favorPoint", charConfig.get("favorPoint"));
            innerChars.put("potentialRank", charConfig.get("potentialRank"));
            innerChars.put("mainSkillLvl", charConfig.get("mainSkillLvl"));
            innerChars.put("skin", skins.get(i));
            innerChars.put("level", level.get(i));
            innerChars.put("exp", 0);
            innerChars.put("evolvePhase", phases.get(i) - 1);

            //amiya
            if (listChars.get(i).equals("char_002_amiya")) {

                LinkedHashMap<String, Object> amiya = new LinkedHashMap<>();
                LinkedHashMap<String, Object> amiya2 = new LinkedHashMap<>();
                LinkedHashMap<Object, Object> tmpl = new LinkedHashMap<>();
                ArrayList<LinkedHashMap> amiyaSkills = new ArrayList<>();
                String[] skillName = {"skchr_amiya2_1", "skchr_amiya2_2"};

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
                    linkedHashMap.put("specializeLevel", charConfig.get("skillsSpecializeLevel"));
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
        //squads
        String loadSquads = FileUtils.readFileToString(new File("src/main/resources/data/config/squads.json"), "utf-8");
        Map<String, Object> squadsMap = JsonUtils.transferToMap(loadSquads);
        //addon
        Map<String, Object> mapAddon = JsonUtils.transferToMap(loadAddon);
        HashMap<Object, Object> handbook = new HashMap<>();
        Map<String, Object> handbookDict = (Map) mapAddon.get("handbookDict");
        for (String charId : handbookDict.keySet()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            ArrayList<String> storySetId = new ArrayList<>();
            if (((Map) handbookDict.get(charId)).get("handbookAvgList") != null) {
                int size = ((ArrayList) ((Map) handbookDict.get(charId)).get("handbookAvgList")).size();
                for (int i = 0; i < size; i++) {
                    String s = (String) ((Map) ((ArrayList) ((Map) handbookDict.get(charId)).get("handbookAvgList")).get(i)).get("storySetId");
                    storySetId.add(s);
                }
                hashMap.put("charId", charId);
                hashMap.put("storySetId", storySetId);
                handbook.put(charId, hashMap);
            } else {
                hashMap.put("charId", charId);
                handbook.put(charId, new HashMap<>());
            }
        }
        HashMap<String, Object> stageData = new HashMap<>();
        Map<String, Object> handbookStageData = (Map) mapAddon.get("handbookStageData");
        for (String CharId : handbookStageData.keySet()) {
            String stageId = (String) ((Map) handbookStageData.get(CharId)).get("stageId");
            stageData.put(CharId, Map.of("charId", CharId,
                    "stageId", stageId));
        }
        for (int i = 0; i < listChars.size(); i++) {
            int size = 0;
            if (handbook.get(listChars.get(i)) != null) {
                size = ((ArrayList) ((Map) handbook.get(listChars.get(i))).get("storySetId")).size();
                if (stageData.containsKey(listChars.get(i)) && size > 0) {
                    LinkedHashMap<String, Object> story = new LinkedHashMap<>();
                    LinkedHashMap<String, Object> stage = new LinkedHashMap<>();
                    for (int j = 0; j < size; j++) {
                        String s = (String) ((ArrayList) ((HashMap) handbook.get(listChars.get(i))).get("storySetId")).get(j);
                        story.put(s, Map.of("fts", 1649232340,
                                "rts", 1649232340));
                    }
                    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("startTimes", 0);
                    linkedHashMap.put("completeTimes", 1);
                    linkedHashMap.put("state", 3);
                    linkedHashMap.put("fts", 1624284657);
                    linkedHashMap.put("rts", 1624284657);
                    linkedHashMap.put("startTime", 2);
                    stage.put((String) ((Map) stageData.get(listChars.get(i))).get("stageId"), linkedHashMap);
                    LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
                    map.put("story", story);
                    map.put("stage", stage);
                    addon.put(listChars.get(i), map);
                } else if (stageData.containsKey(listChars.get(i)) && size < 0) {
                    LinkedHashMap<String, Object> stage = new LinkedHashMap<>();
                    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("startTimes", 0);
                    linkedHashMap.put("completeTimes", 1);
                    linkedHashMap.put("state", 3);
                    linkedHashMap.put("fts", 1624284657);
                    linkedHashMap.put("rts", 1624284657);
                    linkedHashMap.put("startTime", 2);
                    stage.put((String) ((Map) stageData.get(listChars.get(i))).get("stageId"), linkedHashMap);
                    LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
                    map.put("story", new HashMap<>());
                    map.put("stage", stage);
                    addon.put(listChars.get(i), map);

                } else if (!stageData.containsKey(listChars.get(i)) && size > 0) {
                    LinkedHashMap<String, Object> story = new LinkedHashMap<>();
                    for (int j = 0; j < size; j++) {
                        String s = (String) ((ArrayList) ((Map) handbook.get(listChars.get(i))).get("storySetId")).get(j);
                        story.put(s, Map.of("fts", 1649232340,
                                "rts", 1649232340));
                    }
                    LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
                    map.put("story", story);
                    addon.put(listChars.get(i), map);

                } else {
                    HashMap<Object, Object> hashMap = new HashMap<>();
                    hashMap.put("story", new HashMap<>());
                    addon.put(listChars.get(i), hashMap);
                }
            } else if (handbook.get(listChars.get(i)) == null) {
                HashMap<Object, Object> hashMap = new HashMap<>();
                hashMap.put("story", new HashMap<>());
                addon.put(listChars.get(i), hashMap);
            }
        }


        troop.put("curCharInstId", maxInstId);
        troop.put("curSquadCount", 4);

        troop.put("squads", squadsMap);

        troop.put("chars", chars);

        troop.put("charGroup", charGroup);
        troop.put("charMission", new HashMap<>());
        troop.put("addon", addon);

        return troop;
    }

    public LinkedHashMap getNpcAudio() {
        LinkedHashMap<Object, Object> npcAudio = new LinkedHashMap<>();
        Map audio = Map.of("npcShowAudioInfoFlag", "CN_MANDARIN");
        npcAudio.put("char_511_asnipe", audio);
        npcAudio.put("char_510_amedic", audio);
        npcAudio.put("char_508_aguard", audio);
        npcAudio.put("char_509_acast", audio);
        npcAudio.put("char_513_apionr", audio);
        return npcAudio;
    }

    public LinkedHashMap getPushFlags() throws IOException {
        String loadMail = FileUtils.readFileToString(new File("src/main/resources/data/config/mails.json"), "utf-8");
        LinkedHashMap<String, Object> pushFlags = new LinkedHashMap<>();
        Map<String, Object> mail = JsonUtils.transferToMap(loadMail);
        pushFlags.put("hasGifts", ((Map) mail.get("mailList")).size());
        pushFlags.put("hasFriendRequest", 0);
        pushFlags.put("hasClues", 0);
        pushFlags.put("hasFreeLevelGP", 0);
        pushFlags.put("status", timeStamp);

        return pushFlags;
    }

    public Map getSkin() throws IOException {
        LinkedHashMap<String, Object> characterSkins = new LinkedHashMap<>();
        String loadSkin = FileUtils.readFileToString(new File("src/main/resources/data/excel/skin_table.json"), "utf-8");
        Map<String, Object> skins = (Map) JsonUtils.transferToMap(loadSkin).get("charSkins");
        for (String key : skins.keySet()) {
            String skinId = (String) ((Map) skins.get(key)).get("skinId");
            if (skinId.contains("char") && skinId.contains("@")) {
                characterSkins.put(skinId, 1);
            }
        }

        return Map.of("characterSkins", characterSkins);
    }

    public Map getMission() {
        //
        return new HashMap<>();
    }

    public Map getDexNav() throws IOException {
        String loadChars = FileUtils.readFileToString(new File("src/main/resources/data/excel/character_table.json"), "utf-8");
        LinkedHashMap<String, Object> character = new LinkedHashMap<>();
        LinkedHashMap<String, Object> dexNav = new LinkedHashMap<>();
        Map<String, Object> chars = JsonUtils.transferToMap(loadChars);
        for (String key : chars.keySet()) {
            String charInstId = StringUtils.substringBetween(key, "_");
            if (!key.contains("char")) {
                continue;
            }
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("charInstId", charInstId);
            linkedHashMap.put("count", 6);       //config maybe
            character.put(key, linkedHashMap);
        }
        dexNav.put("character", character);
        dexNav.put("formula", new HashMap<>());
        dexNav.put("enemy", new HashMap<>());
        dexNav.put("teamV2", new HashMap<>());


        return dexNav;
    }

    public LinkedHashMap<String, Object> getBackFlow() {
        LinkedHashMap<String, Object> backFlow = new LinkedHashMap<>();
        backFlow.put("open", true);
        backFlow.put("current", null);
        return backFlow;
    }

    public ArrayList<Map> getAvatarAndBackground() throws IOException {
        ArrayList<Map> getAvatarAndBackground = new ArrayList<>();
        LinkedHashMap<String, Object> avatar_icon = new LinkedHashMap<>();
        LinkedHashMap<String, Object> avatarId = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> bgs = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> background = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> themes = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> homeTheme = new LinkedHashMap<>();
        //avatar
        String loadAvatar = FileUtils.readFileToString(new File("src/main/resources/data/excel/display_meta_table.json"), "utf-8");
        Map<String, Object> mapAvatar = (Map) JsonUtils.transferToMap(loadAvatar).get("playerAvatarData");
        ArrayList listAvatar = (ArrayList) mapAvatar.get("avatarList");
        for (int i = 0; i < listAvatar.size(); i++) {
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            String avatar =(String) ((Map) listAvatar.get(i)).get("avatarId");
            String src = (avatar.contains("def") ? "initial" : "other");
            linkedHashMap.put("ts", timeStamp);
            linkedHashMap.put("src", src);
            avatarId.put(avatar , linkedHashMap);
        }
        //background
        Map<String, Object> mapBackground = (Map) JsonUtils.transferToMap(loadAvatar).get("homeBackgroundData");
        ArrayList listBackground = (ArrayList) mapBackground.get("homeBgDataList");
        for (int i = 0; i < listBackground.size(); i++) {
            bgs.put(((Map) listBackground.get(i)).get("bgId"), Map.of("unlock", timeStamp));
        }
        //theme
        ArrayList listTheme = (ArrayList) mapBackground.get("themeList");
        for (int i = 0; i < listTheme.size(); i++) {
            themes.put(((Map) listTheme.get(i)).get("id"), Map.of("unlock", ((int) 1695000000)));
        }

        avatar_icon.put("avatar_icon", avatarId);
        background.put("selected", Confing.getUserConfig().get("background"));
        background.put("bgs", bgs);
        homeTheme.put("selected", Confing.getUserConfig().get("theme"));
        homeTheme.put("themes", themes);
        getAvatarAndBackground.add(avatar_icon);
        getAvatarAndBackground.add(background);
        getAvatarAndBackground.add(homeTheme);
        return getAvatarAndBackground;
    }

    public Map getRlv2() throws IOException {
        String loadRlv2 = FileUtils.readFileToString(new File("src/main/resources/data/config/rlv2.json"), "utf-8");
        return JsonUtils.transferToMap(loadRlv2);
    }

    public LinkedHashMap getDeepSea() throws IOException {
        LinkedHashMap<String, Object> deepSea = new LinkedHashMap<>();
        LinkedHashMap<String, Object> places = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nodes = new LinkedHashMap<>();
        LinkedHashMap<String, Object> choices = new LinkedHashMap<>();
        LinkedHashMap<String, Object> events = new LinkedHashMap<>();
        LinkedHashMap<String, Object> treasures = new LinkedHashMap<>();
        LinkedHashMap<String, Object> stories = new LinkedHashMap<>();
        LinkedHashMap<String, Object> techTrees = new LinkedHashMap<>();
        Map<String, Object> logs = new HashMap<>();
        String loadActivity = FileUtils.readFileToString(new File("src/main/resources/data/excel/activity_table.json"), "utf-8");
        //ognl
        Map Activity = JsonUtils.getValue(loadActivity, "activity.TYPE_ACT17SIDE.act17side", Map.class);
        //places
        Map<String, Object> placeDataMap = (Map) Activity.get("placeDataMap");
        for (String key : placeDataMap.keySet()) {
            places.put(key, 2);
        }
        //nodes
        Map<String, Object> nodeInfoDataMap = (Map) Activity.get("nodeInfoDataMap");
        for (String key : nodeInfoDataMap.keySet()) {
            nodes.put(key, 2);
        }
        //choices
        Map<String, Object> choiceNodeDataMap = (Map) Activity.get("choiceNodeDataMap");
        for (String key : choiceNodeDataMap.keySet()) {
            ArrayList<Object> list = new ArrayList<>();
            ArrayList optionList = JsonUtils.getValueFromMap(choiceNodeDataMap, key + ".optionList", ArrayList.class);
            for (int i = 0; i < optionList.size(); i++) {
                list.add(2);
            }
            choices.put(key, list);
        }
        //events
        Map<String, Object> eventDataMap = (Map) Activity.get("eventDataMap");
        for (String key : eventDataMap.keySet()) {
            events.put(key, 1);
        }
        //treasures
        Map<String, Object> treasureNodeDataMap = (Map) Activity.get("treasureNodeDataMap");
        for (String key : treasureNodeDataMap.keySet()) {
            treasures.put(key, 1);
        }
        //stories
        Map<String, Object> storyNodeDataMap = (Map) Activity.get("storyNodeDataMap");
        for (String key : storyNodeDataMap.keySet()) {
            stories.put(key, 1);
        }
        //techTrees
        Map<String, Object> techTreeDataMap = (Map) Activity.get("techTreeDataMap");
        for (String key : techTreeDataMap.keySet()) {
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            String defaultBranchId = (String) ((Map) techTreeDataMap.get(key)).get("defaultBranchId");
            linkedHashMap.put("state", 2);
            linkedHashMap.put("branch", defaultBranchId);
            techTrees.put(key, linkedHashMap);
        }
        //logs
        Map<String, Object> archiveItemUnlockDataMap = (Map) Activity.get("archiveItemUnlockDataMap");
        ArrayList tempLogs = new ArrayList<>();
        for (String key : archiveItemUnlockDataMap.keySet()) {
            if (key.contains("log")) {
                String chapterId = (String) ((Map) archiveItemUnlockDataMap.get(key)).get("chapterId");
                String itemId = (String) ((Map) archiveItemUnlockDataMap.get(key)).get("itemId");
                tempLogs.add(MapUtil.of(chapterId, itemId));
            }
        }
        logs = MapUtil.toListMap(tempLogs);

        deepSea.put("places", places);
        deepSea.put("nodes", nodes);
        deepSea.put("choices", choices);
        deepSea.put("events", events);
        deepSea.put("treasures", treasures);
        deepSea.put("stories", stories);
        deepSea.put("techTrees", techTrees);
        deepSea.put("logs", logs);

        return deepSea;
    }

    public Map getTower() throws IOException {
        String loadTower = FileUtils.readFileToString(new File("src/main/resources/data/config/tower.json"), "utf-8");
        String seasonId = (String) Confing.getTowerConfig().get("season");
        Map<String, Object> tower = JsonUtils.transferToMap(loadTower);
        Map season = (Map) tower.get("season");
        season.put("id", seasonId);
        return tower;
    }

    public Map getSiracusaMap() {
        LinkedHashMap<Object, Object> area = new LinkedHashMap<>();
        area.put("area_centro", 1);
        area.put("area_rione", 1);
        area.put("area_saluzzo", 1);
        area.put("area_bellone", 1);
        area.put("area_rossati", 1);
        area.put("area_teatro", 1);
        area.put("area_torre", 1);
        area.put("area_comando", 1);
        area.put("area_municipio", 1);
        return Map.of("area", area);
    }

    public LinkedHashMap getRetro() throws IOException {
        LinkedHashMap<String, Object> retro = new LinkedHashMap<>();
        LinkedHashMap<String, Object> block = new LinkedHashMap<>();
        LinkedHashMap<String, Object> trail = new LinkedHashMap<>();
        String loadRetro = FileUtils.readFileToString(new File("src/main/resources/data/excel/retro_table.json"), "utf-8");
        Map<String, Object> retroTable = JsonUtils.transferToMap(loadRetro);
        //block
        Map<String, Object> retroActList = (Map) retroTable.get("retroActList");
        for (String key : retroActList.keySet()) {
            block.put(key, Map.of("locked", 0, "open", 1));
        }
        //trail
        Map<String, Object> retroTrailList = (Map) retroTable.get("retroTrailList");
        for (String key : retroTrailList.keySet()) {
            ArrayList trailRewardList = JsonUtils.getValueFromMap(retroTrailList, key + ".trailRewardList", ArrayList.class);
            String trailRewardId = (String) ((Map) trailRewardList.get(trailRewardList.size() - 1)).get("trailRewardId");
            trail.put(key, Map.of(trailRewardId, 1));
        }

        retro.put("coin", 2);
        retro.put("supplement", 1);
        retro.put("block", block);
        retro.put("trail", trail);
        retro.put("lst", 0);
        retro.put("nst", 0);
        return retro;
    }

    public HashMap getRoguelike() {
        HashMap<Object, Object> roguelike = new HashMap<>();
        roguelike.put("current", null);
        roguelike.put("stable", null);
        return roguelike;
    }

    public LinkedHashMap getCampaignsV2() throws IOException {
        LinkedHashMap<String, Object> campaignsV2 = new LinkedHashMap<>();
        LinkedHashMap<String, Object> instances = new LinkedHashMap<>();
        LinkedHashMap<String, Object> open = new LinkedHashMap<>();
        LinkedHashMap<String, Object> missions = new LinkedHashMap<>();
        LinkedHashMap<String, Object> sweepMaxKills = new LinkedHashMap<>();
        ArrayList<Object> listCamp = new ArrayList<>();
        String loadStages = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"), "utf-8");
        Map<String, Object> stageTable = JsonUtils.transferToMap(loadStages);
        Map<String, Object> stages = (Map) stageTable.get("stages");
        //instances
        for (String key : stages.keySet()) {
            if (key.contains("camp")) {
                LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("maxKills", 400);
                linkedHashMap.put("rewardStatus", List.of(1, 1, 1, 1, 1, 1, 1, 1));
                instances.put(key, linkedHashMap);
                listCamp.add(key);
            }
        }
        //open
        open.put("permanent", listCamp);
        open.put("rotate", "camp_r_13");
        open.put("rGroup", "group_r_13");
        open.put("training", listCamp);
        open.put("tGroup", "group_tr_23");
        open.put("tAllOpen", "group_tr_all_4");
        //missions
        missions.putAll(Map.of("exterminateActivity_1", 2,
                "exterminateActivity_2", 2,
                "exterminateActivity_3", 2,
                "exterminateActivity_4", 2));
        //sweepMaxKills
        for (Object o : listCamp) {
            sweepMaxKills.put((String) o, 400);
        }

        campaignsV2.put("campaignCurrentFee", 0);
        campaignsV2.put("campaignTotalFee", 9999);
        campaignsV2.put("lastRefreshTs", 0);
        campaignsV2.put("instances", instances);
        campaignsV2.put("open", open);
        campaignsV2.put("missions", missions);
        campaignsV2.put("sweepMaxKills", sweepMaxKills);
        return campaignsV2;
    }

    public LinkedHashMap getCheckIn() {
        LinkedHashMap<Object, Object> checkIn = new LinkedHashMap<>();
        checkIn.put("canCheckIn", 0);
        checkIn.put("checkInGroupId", null);
        checkIn.put("checkInRewardIndex", 0);
        checkIn.put("checkInHistory", new ArrayList<>());

        return checkIn;
    }

    public Map getCharm() throws IOException {
        Map<Object, Object> charm = new HashMap<>();
        LinkedHashMap<Object, Object> charms = new LinkedHashMap<>();
        String loadCharm = FileUtils.readFileToString(new File("src/main/resources/data/excel/charm_table.json"), "utf-8");
        ArrayList charmList = (ArrayList) ((Map) JsonUtils.transferToMap(loadCharm)).get("charmList");
        for (int i = 0; i < charmList.size(); i++) {
            String id = (String) ((Map) charmList.get(i)).get("id");
            charms.put(id, 1);
        }
        charm.put("charms", charms);
        charm.put("squad", new ArrayList<>());

        return charm;
    }

    public LinkedHashMap getCar() throws IOException {
        LinkedHashMap<String, Object> car = new LinkedHashMap<>();
        LinkedHashMap<String, Object> battleCar = new LinkedHashMap<>();
        LinkedHashMap<String, Object> exhibitionCar = new LinkedHashMap<>();
        LinkedHashMap<String, Object> accessories = new LinkedHashMap<>();
        String loadActivity = FileUtils.readFileToString(new File("src/main/resources/data/excel/activity_table.json"), "utf-8");
        Map carData = (Map) JsonUtils.transferToMap(loadActivity).get("carData");
        //battleCar
        battleCar.put("ROOF", null);
        battleCar.put("HEADSTOCK", null);
        battleCar.put("TRUNK_01", null);
        battleCar.put("TRUNK_02", null);
        battleCar.put("CAR_OS_01", null);
        battleCar.put("CAR_OS_02", null);
        //exhibitionCar
        exhibitionCar.put("ROOF", null);
        exhibitionCar.put("HEADSTOCK", null);
        exhibitionCar.put("TRUNK_01", null);
        exhibitionCar.put("TRUNK_02", null);
        exhibitionCar.put("CAR_OS_01", null);
        exhibitionCar.put("CAR_OS_02", null);
        //accessories
        Map<String, Object> carDict = (Map) carData.get("carDict");
        for (String key : carDict.keySet()) {
            int num = ((ArrayList) ((Map) carDict.get(key)).get("posList")).size();
            accessories.put(key, Map.of("id", key, "num", num));
        }

        car.put("battleCar", battleCar);
        car.put("exhibitionCar", exhibitionCar);
        car.put("accessories", accessories);
        return car;
    }

}

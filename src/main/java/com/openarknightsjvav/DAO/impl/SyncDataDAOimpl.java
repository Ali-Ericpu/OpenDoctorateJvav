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

        //chars
        Integer maxInstId = 0;        //curCharInstId
        ArrayList<Integer> defaultSkillIndex = new ArrayList();     //skillIndex
        ArrayList<Integer> phases = new ArrayList();         //phases
        Map<String, Double> charConfig = Confing.getCharConfig();
        String loadChars = FileUtils.readFileToString(new File("src/main/resources/data/excel/character_table.json"), "utf-8");
        ArrayList listChar = new ArrayList();
        ArrayList listInstId = new ArrayList();
        LinkedHashMap chars = new LinkedHashMap();
        ArrayList skillCount;
        ArrayList evolvePhase;
        ArrayList<Double> level = new ArrayList();
        ArrayList<ArrayList> listLevel = new ArrayList();
        ArrayList<ArrayList> listSkills = new ArrayList();
        ArrayList skills = new ArrayList();

        Map<String, Object> mapChar = JsonUtils.transferToMap(loadChars);
        Set<String> keySet = mapChar.keySet();
        for (String key : keySet) {
            if (key.contains("char")) {
                listChar.add(key);
                //将substring处理过后的key放入list
                int tempInstId = Integer.parseInt(StringUtils.substringBetween(key, "_"));
                listInstId.add(tempInstId);
                if (tempInstId > maxInstId) {
                    maxInstId = tempInstId + 1;
                }
            }
        }
        for (int i = 0; i < listChar.size(); i++) {
            Map mapChars = (Map) mapChar.get(listChar.get(i));
            //skillindex
            skillCount = (ArrayList) mapChars.get("skills");
            listSkills.add(i,skillCount);
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
            for (int i = 0; i < listChar.size(); i++) {
                Map mapLevel = (Map) (listLevel.get(i).get(phases.get(i) - 1));
                level.add((Double) mapLevel.get("maxLevel"));
            }
        } else {
            for (int i = 0; i < listChar.size(); i++) {
                level.add(charConfig.get("level"));
            }
        }

        //skills

        for (int i = 0; i < listChar.size(); i++) {
            if ((listSkills.get(i)).size() >= 1){
                for (int j = 0; j < defaultSkillIndex.size(); j++) {
                    Map<String, Object> skillId = Map.of("skillId", listChar.get(i),
                            "unlock", 1,
                            "state", 0,
                            "specializeLevel", charConfig.get("skillsSpecializeLevel").intValue(),
                            "completeUpgradeTime", -1);
                    skills.add(skillId);
                }
            }else if((listSkills.get(i)).size() == 0){
                Map<String, Object> skillId = Map.of("skillId", listChar.get(i),
                        "unlock", 1,
                        "state", 0,
                        "specializeLevel", charConfig.get("skillsSpecializeLevel").intValue(),
                        "completeUpgradeTime", -1);
                skills.add(skillId);
            }else{
                skills.add(new HashMap());
            }
        }

        for (int i = 0; i < listChar.size(); i++) {
            LinkedHashMap innerChars = new LinkedHashMap();
            innerChars.put("instId", listInstId.get(i));
            innerChars.put("charId", listChar.get(i));
            innerChars.put("favorPoint", charConfig.get("favorPoint").intValue());
            innerChars.put("potentialRank", charConfig.get("potentialRank").intValue());
            innerChars.put("mainSkillLvl", charConfig.get("mainSkillLvl").intValue());
            innerChars.put("skin", listChar.get(i) + "#1");
            innerChars.put("level", level.get(i).intValue());
            innerChars.put("exp", 0);
            innerChars.put("evolvePhase", phases.get(i) - 1);
            innerChars.put("defaultSkillIndex", defaultSkillIndex.get(i) - 1);
            innerChars.put("gainTime", timeStamp);
            innerChars.put("skills", skills);
            innerChars.put("voiceLan", "JP");
            innerChars.put("currentEquip", null);
            innerChars.put("equip", new HashMap());
            innerChars.put("starMark", 0);
            chars.put(listInstId.get(i), innerChars);
        }


        troop.put("curCharInstId", maxInstId);
        troop.put("curSquadCount", 4);

        //config
        String loadSquads = FileUtils.readFileToString(new File("src/main/resources/data/config/squads.json"), "utf-8");
        Map<String, Object> squadsMap = JsonUtils.transferToMap(loadSquads);
        troop.put("squads", squadsMap);


        troop.put("chars", chars);
        troop.put("charGroup", 4);
        troop.put("charMission", 4);
        troop.put("addon", 4);

        return troop;
    }

}

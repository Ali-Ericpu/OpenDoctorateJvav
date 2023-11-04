package com.openarknightsjvav.DAO.impl;

import com.openarknightsjvav.DAO.SyncDataDAO;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
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



    private Long timeStamp = System.currentTimeMillis()/1000;
    @Override
    public LinkedHashMap getDungeon() throws IOException {

        LinkedHashMap dungeon = new LinkedHashMap();
        ArrayList listCowLevel = new ArrayList();
        //读取json转为string
        String stage = FileUtils.readFileToString(new File("src/main/resources/data/excel/stage_table.json"));
        //调用工具类将读取到的字符串转化为map
        Map<String, Object> map = JsonUtils.transferToMap(stage);
        //获取key为stages的value并重新封装为一个map
        Map stagesKey = (Map) map.get("stages");
        //遍历stages的所有key并封装到list中
        ArrayList listStages = new ArrayList();
        for (Object key : stagesKey.keySet()) {
            listStages.add(key);
            String s = (String) key;
            if (s.contains("spst")){
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
        dungeon.put("stages",stages);

        LinkedHashMap putCowLevel = new LinkedHashMap();
        LinkedHashMap cowlevel = new LinkedHashMap();
        for (int i = 0; i < listCowLevel.size(); i++) {
            LinkedHashMap innerCowLevel = new LinkedHashMap();
            innerCowLevel.put("id",listCowLevel.get(i));
            innerCowLevel.put("type","STAGE");
            innerCowLevel.put("var",new Boolean[]{true});
            innerCowLevel.put("fts",timeStamp-100+i);
            innerCowLevel.put("rts",timeStamp-50+i);
            putCowLevel.put(listCowLevel.get(i),innerCowLevel);
            cowlevel.putAll(putCowLevel);
        }
        dungeon.put("cowLevel",cowlevel);
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
        status.put("nickName","Doctorate");
        status.put("nickNumber","1111");

        status.put("level",120);
        status.put("exp","0");
        status.put("socialPoint",6);
        status.put("gachaTickt",0);
        status.put("tenGachaTicket",0);
        status.put("instantFinishTicket",0);
        status.put("hggShard",0);
        status.put("lggShard",0);
        status.put("recruitLicense",0);
        status.put("progress",30000);
        status.put("buyApRemainTimes",99);
        status.put("apLimitUpFlag",0);
        status.put("uid","1111");

        flags.put("init",1);
        String s = FileUtils.readFileToString(new File("src/main/resources/data/excel/story_table.json"));
        Map<String, Object> flagsMap = JsonUtils.transferToMap(s);
        Set<String> keySet = flagsMap.keySet();
        for(String key : keySet) {
            flags.put(key,1);
        }
        status.put("flags",flags);

        status.put("ap",130);
        status.put("maxAp",130);
        status.put("androidDiamond",0);
        status.put("iosDiamond",0);
        status.put("diamondShard",0);
        status.put("gold",0);
        status.put("practiceTicket",0);
        status.put("lastRefreshTs",timeStamp);
        status.put("lastApAddTime",timeStamp);
        status.put("mainStageProgress",null);
        status.put("registerTs",timeStamp);
        status.put("lastOnlineTs",timeStamp);
        status.put("serverName","Doctorate");
        status.put("avatarId",0);
        status.put("resume","DoctorateJvav");
        status.put("friendNumLimit",999);
        status.put("monthlySubscriptionStartTime",0);
        status.put("monthlySubscriptionEndTime",0);
        //config
        status.put("secretary","char_249_mlyss");
        status.put("secretarySkinId","char_249_mlyss#2");

        status.put("tipMonthlyCardExpireTs",0);
        //config
        LinkedHashMap avatar = new LinkedHashMap();
        avatar.put("type","ICON");
        avatar.put("id","avatar_activity_RI");
        status.put("avatar",avatar);

        //config
        status.put("globalVoiceLan","JP");

        return status;
    }

}

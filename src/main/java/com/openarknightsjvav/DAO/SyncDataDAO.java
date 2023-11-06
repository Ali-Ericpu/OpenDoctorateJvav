package com.openarknightsjvav.DAO;

import java.io.IOException;
import java.util.*;

/**
 * ClassName: syncDataDAO
 * Package: com.openarknightsjvav.DAO
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/3 13:24
 * @Version 1.0
 */
public interface SyncDataDAO {
    LinkedHashMap getActivity();

    LinkedHashMap getStatus() throws IOException;

    LinkedHashMap getDungeon() throws IOException;

    LinkedHashMap getTroop() throws IOException;

    LinkedHashMap getNpcAudio();

    LinkedHashMap getPushFlags() throws IOException;

    Map getSkin() throws IOException;

    Map getMission();

    Map getDexNav() throws IOException;

    LinkedHashMap getBackFlow();

    ArrayList getAvatarAndBackground() throws IOException;


    Map getRlv2() throws IOException;

    LinkedHashMap getDeepSea() throws IOException;

    Map getTower() throws IOException;

    Map getSiracusaMap();

    LinkedHashMap getRetro() throws IOException;
}

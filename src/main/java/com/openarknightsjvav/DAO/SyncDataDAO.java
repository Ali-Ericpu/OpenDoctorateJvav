package com.openarknightsjvav.DAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    LinkedHashMap getAvatar() throws IOException;
}

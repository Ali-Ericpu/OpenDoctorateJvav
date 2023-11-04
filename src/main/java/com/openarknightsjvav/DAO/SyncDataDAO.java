package com.openarknightsjvav.DAO;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

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
}

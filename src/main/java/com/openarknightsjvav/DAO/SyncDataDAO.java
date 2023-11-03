package com.openarknightsjvav.DAO;

import java.io.IOException;
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
    List getStages() throws IOException;
}

package com.openarknightsjvav.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: syncDataService
 * Package: com.cryrain.openarknightsjava.service
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/2 19:41
 * @Version 1.0
 */

public interface SyncDataService {
    Map getSyncData() throws IOException;

}

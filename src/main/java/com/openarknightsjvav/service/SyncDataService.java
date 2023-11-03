package com.openarknightsjvav.service;

import java.io.IOException;
import java.util.LinkedHashMap;

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
    LinkedHashMap getSyncData() throws IOException;

}

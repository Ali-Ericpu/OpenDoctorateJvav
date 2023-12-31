package com.openarknightsjvav.service;

import com.openarknightsjvav.DAO.ProdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ProdService
 * Package: com.openarknightsjvav.service
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/6 19:05
 * @Version 1.0
 */
@Service
public class ProdService {

    @Autowired
    private ProdDAO prodDAO;

    public String getNetworkConfig() {
/*        HashMap<String, Object> networkConfig = new HashMap<>();
        networkConfig.put("sign", "sign");
        networkConfig.put("content", prodDAO.getNetworkConfig());*/
        return prodDAO.getNetworkConfig();
    }

    public Map getVersion() throws IOException {
        return prodDAO.getVersion();
    }

    public Map getAPPConfig() {
        return prodDAO.getAPPConfig();
    }

    public Map getNotice() throws IOException {
        return prodDAO.getNotice();
    }

    public Map getRemote() throws IOException {
        return prodDAO.getRemote();
    }

    public Map getBasic() {
        return prodDAO.getBasic();
    }

    public ResponseEntity<InputStreamResource> getAsset(String assetsHash, String fileName) throws IOException {

        return prodDAO.getAsset(assetsHash,fileName);
    }
}

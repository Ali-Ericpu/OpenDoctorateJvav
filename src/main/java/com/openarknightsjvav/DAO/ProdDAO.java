package com.openarknightsjvav.DAO;

import com.openarknightsjvav.utils.Confing;
import com.openarknightsjvav.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ProdDAO
 * Package: com.openarknightsjvav.DAO
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/6 19:10
 * @Version 1.0
 */
@Repository
public class ProdDAO {
    public String getNetworkConfig(){
/*        Map networkConfig = Confing.getNetworkConfig();
        Map serverConfig = Confing.getServerConfig();
        String host = (String) serverConfig.get("host");
        Integer port = ((Double) serverConfig.get("port")).intValue();
        String server = "http://" + host + ":" + port;
        String mode = (String) serverConfig.get("mode");
        String funcVer = JsonUtils.getValueFromMap(networkConfig, mode + ".content.funcVer", String.class);
        Map<String, Object> network = JsonUtils.getValueFromMap(networkConfig, mode + ".content.configs." + funcVer + ".network", Map.class);
        network.put("gs", server);
        network.put("as", server);
        network.put("u8", server + "/u8");
        network.put("hu", server + "/assetbundle/official");
        network.put("hv", server + "/config/prod/official/{0}/version");
        network.put("rc", server + "/config/prod/official/remote_config");
        network.put("an", server + "/config/prod/announce_meta/{0}/announcement.meta.json");
        network.put("prean", server + "/config/prod/announce_meta/{0}/preannouncement.meta.json");

        Map content = (Map) ((Map) networkConfig.get(mode)).get("content");*/

        String content = "{\"sign\": \"sign\", \"content\": \"{\\\"configVer\\\": \\\"5\\\", \\\"funcVer\\\": \\\"V044\\\", \\\"configs\\\": {\\\"V044\\\": {\\\"override\\\": true,\n" +
                "\\\"network\\\": {\\\"gs\\\": \\\"http://127.0.0.1:8443\\\", \\\"as\\\": \\\"http://127.0.0.1:8443\\\", \\\"u8\\\":\n" +
                "\\\"http://127.0.0.1:8443/u8\\\", \\\"hu\\\": \\\"http://127.0.0.1:8443/assetbundle/official\\\", \\\"hv\\\":\n" +
                "\\\"http://127.0.0.1:8443/config/prod/official/{0}/version\\\", \\\"rc\\\":\n" +
                "\\\"http://127.0.0.1:8443/config/prod/official/remote_config\\\", \\\"an\\\":\n" +
                "\\\"http://127.0.0.1:8443/config/prod/announce_meta/{0}/announcement.meta.json\\\", \\\"prean\\\":\n" +
                "\\\"http://127.0.0.1:8443/config/prod/announce_meta/{0}/preannouncement.meta.json\\\", \\\"sl\\\":\n" +
                "\\\"https://ak.hypergryph.com/protocol/service\\\", \\\"of\\\": \\\"https://ak.hypergryph.com/index.html\\\", \\\"pkgAd\\\": null,\n" +
                "\\\"pkgIOS\\\": null, \\\"secure\\\": false}}}}\"}";

        return content;
    }

    public Map getRemote() throws IOException {
        return (Map) Confing.getConfig().get("remote");
    }

    public Map getVersion() throws IOException {
        return (Map) ((Map) Confing.getConfig().get("version")).get("android");
    }

    public Map getAPPConfig() {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("antiAddiction", Map.of("minorPeriodEnd", 21, "minorPeriodStart", 20));
        data.put("payment", List.of(Map.of("key", "alipay", "recommend", true),
                Map.of("key", "wechat", "recommend", false),
                Map.of("key", "pcredit", "recommend", false)));
        data.put("customerServiceUrl", "https://chat.hypergryph.com/chat/h5/v2/index.html");
        data.put("cancelDeactivateUrl", "https://user.hypergryph.com/cancellation");
        data.put("agreementUrl", Map.of("game", "https://user.hypergryph.com/protocol/plain/ak/index",
                "unbind", "https://user.hypergryph.com/protocol/plain/ak/cancellation",
                "account", "https://user.hypergryph.com/protocol/plain/index",
                "privacy", "https://user.hypergryph.com/protocol/plain/privacy",
                "register", "https://user.hypergryph.com/protocol/plain/registration",
                "updateOverview", "https://user.hypergryph.com/protocol/plain/overview_of_changes",
                "childrenPrivacy", "https://user.hypergryph.com/protocol/plain/children_privacy"));
        data.put("app", Map.of("enablePayment", true, "enableAutoLogin", false,
                "enableAuthenticate", true, "enableAntiAddiction", true,
                "wechatAppId", "", "alipayAppId", "",
                "oneLoginAppId", "", "enablePaidApp", false,
                "appName", "明日方舟", "appAmount", 600));
        return data;
    }

    public Map getNotice() throws IOException {
        String Preannouncement = FileUtils.readFileToString(new File("src/main/resources/data/config/Preannouncement.json"), "utf-8");
        return JsonUtils.transferToMap(Preannouncement);
    }

    public Map getBasic() {
        return Map.of("hgId", "1",
                "phone", "doctorate",
                "email", "doctorate",
                "identityNum", "doctorate",
                "identityName", "doctorate",
                "isMinor", false,
                "isLatestUserAgreement", true);
    }


    public File getAsset(String assetsHash, String fileName) throws IOException {

        String url = "https://ak.hycdn.cn/assetbundle/official/Android/assets/" + assetsHash + "/" + fileName;
        if (fileName.equals("hot_update_list.json")){
            FileUtils.writeStringToFile(new File("src/main/resources/data/asset/hot_update_list.json"),
                    JsonUtils.fromUrl(url), "utf-8");
            return new File("src/main/resources/data/asset/hot_update_list.json");
        }else {
            String downloadFilePath = "src/main/resources/data/asset" + assetsHash;
            JsonUtils.writeByteFileFromUrlToLocal(url,downloadFilePath);
            return new File(downloadFilePath + "/" + fileName);
        }

    }
}

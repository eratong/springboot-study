package com.ontg.demo.ocr.util;

import org.json.JSONObject;
import com.ontg.demo.ocr.pojo.BaiduAccount;
import com.ontg.demo.ocr.pojo.BaiduItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BaiduOcr {

    private static final String OCRURL = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";

    @Autowired
    private LocalCache localCache;

    public boolean doOcr(ImgFeature feature, Account account) {
        String value = account.getValue();
        BaiduAccount config = JSONUtil.toBean(value, BaiduAccount.class);

        //调用百度ocr
        List<BaiduItem> baiduItems = doPost(feature, config);

        //结果格式化
        buildResult(baiduItems, feature);
        return true;
    }

    @Override
    public ImgFeature getImgFeature(TreeMap<Integer, ImgData> imgData, OcrContext ocrContext, Integer clearance) {
        clearance = 38;
        return super.getImgFeature(imgData, ocrContext, clearance);
    }

    @Override
    public List<ImgFeature> buildOcrMat(TreeMap<Integer, ImgData> imgData, OcrContext ocrContext, Integer itemNumber) {
        itemNumber = 65;
        return super.buildOcrMat(imgData, ocrContext, itemNumber);
    }

    private List<BaiduItem> doPost(ImgFeature feature, BaiduAccount config) {
        Mat ocrMat = feature.getOcrMat();
        String base64 = ImageDataUtil.opencvMat2Base64ForTencent(ocrMat);
//        Imgcodecs.imwrite("/NFS_ts2/testocr/"+new Date().getTime()+"baidu"+".png",feature.getOcrMat());

        // 请求url
        try {
            String imgParam = URLEncoder.encode(base64, "UTF-8");
            String param = "image=" + imgParam + "&recognize_granularity=small";
//&vertexes_location=true&paragraph=true&probability=true
            // access_token有过期时间，可自行缓存，过期后重新获取。
            String accessToken = getAuth(config.getAppkey(), config.getSecretkey());

            String result = HttpUtil.post(OCRURL, accessToken, param);

            return buildItems(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getAuth(String ak, String sk) {
        String accessToken = "";

        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            //返回结果
            JSONObject jsonObject = new JSONObject(result);
            accessToken = jsonObject.getString("access_token");
            Object expiresIn = jsonObject.get("expires_in");
        } catch (Exception e) {
            log.info("获取token失败!" + e);
        }
        return accessToken;
    }
}

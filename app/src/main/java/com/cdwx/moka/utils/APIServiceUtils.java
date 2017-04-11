package com.cdwx.moka.utils;

import com.cdwx.moka.app.App;
import com.cdwx.moka.model.MainInfoBean;
import com.cdwx.moka.net.APIService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import rx.Observable;

/**
 * Created by KeKe on 2017/4/11.
 */

public class APIServiceUtils extends HttpUtils {
    private APIService apiService;

    public APIServiceUtils(APIService apiService) {
        this.apiService = apiService;
    }

    private String encode(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", MokaUtil.getAppVersionName(App.getInstance()));
            jsonObject.put("userId", UserInfoHelper.getInstance().getUserId());
            jsonObject.put("deviceId", App.deviceID);
            if (params != null && params.size() > 0) {
                Set<Map.Entry<String, String>> set = params.entrySet();
                for (Map.Entry<String, String> entry : set) {
                    jsonObject.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Base64.encode(jsonObject.toString().getBytes());
    }

    public Observable<MainInfoBean> fetchMainInfoData() {
        return apiService.fetchMainInfoData(encode(null));
    }
}

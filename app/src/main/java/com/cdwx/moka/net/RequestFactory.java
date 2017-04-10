package com.cdwx.moka.net;


import com.cdwx.moka.app.MokaApplication;
import com.cdwx.moka.model.ResponseBean;
import com.cdwx.moka.utils.Base64;
import com.cdwx.moka.utils.MokaUtil;
import com.cdwx.moka.utils.UserInfoHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import rx.Subscriber;

/**
 * 网络请求
 */
public class RequestFactory extends RetrofitHttpUtil {

    public static RequestFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        static RequestFactory INSTANCE = new RequestFactory();
    }


    /**
     * 加上通用参数 version 和userID以及加密
     *
     * @param params 请求参数
     * @return 加密后的请求参数
     */
    private String encode(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", MokaUtil.getAppVersionName(MokaApplication.getInstance()));
            jsonObject.put("userId", UserInfoHelper.getInstance().getUserId());
            jsonObject.put("deviceId", MokaApplication.deviceID);
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

    /**
     * 登录
     *
     * @param params
     * @param subscriber
     */
    public void login(Map<String, String> params, Subscriber<ResponseBean> subscriber) {
        toSubscribe(apiService.login(encode(params)), subscriber);
    }


}

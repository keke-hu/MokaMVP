package com.cdwx.moka.net;

import com.cdwx.moka.model.ResponseBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 具体的网络请求
 */
interface APIService {
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResponseBean> login(@Field("data") String data);//登录

    @GET
    Call<ResponseBody> loadFile(@Url String fileUrl);//下载文件

}

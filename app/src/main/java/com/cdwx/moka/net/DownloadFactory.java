package com.cdwx.moka.net;

import android.content.Context;

import com.mmote.mmote.MmoteApplication;
import com.mmote.mmote.constants.MmoteConstants;
import com.mmote.mmote.filedownload.FileCallback;
import com.mmote.mmote.filedownload.FileResponseBody;
import com.mmote.mmote.util.MmoteUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KeKe on 2017/2/20.
 * 下载
 */

public class DownloadFactory extends RetrofitHttpUtil {
    private static final String TAG = "OkHttp";

    public static DownloadFactory getInstance() {
        return DownloadFactory.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        static DownloadFactory INSTANCE = new DownloadFactory();
    }

    private DownloadFactory(){
        resetRetrofit(MmoteApplication.getInstance());
    }

    private void resetRetrofit(Context context) {
        this.mContext = context;
        initOkHttp();
        initRetrofit();
        if (apiService == null) {
            apiService = retrofit.create(APIService.class);
        }
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //打印请求log日志
        if (MmoteConstants.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(MmoteUtil.getCacheDir(mContext), "httpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!MmoteUtil.isNetworkConnected(mContext) || isUseCache) {//如果网络不可用或者设置只用网络
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                } else if (MmoteUtil.isNetworkConnected(mContext) && !isUseCache) {//网络可用
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                }
                Response response = chain.proceed(request);
                if (MmoteUtil.isNetworkConnected(mContext)) {//如果网络可用
                    response = response.newBuilder()
                            //覆盖服务器响应头的Cache-Control,用我们自己的,因为服务器响应回来的可能不支持缓存
                            .header("Cache-Control", "public,max-age=" + maxCacheTime)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;

            }
        };
        builder.cache(cache);
        builder.interceptors().add(cacheInterceptor);//添加本地缓存拦截器，用来拦截本地缓存
        builder.networkInterceptors().add(cacheInterceptor);//添加网络拦截器，用来拦截网络数据
        //设置头部
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("User-Agent", System.getProperty("http.agent"))
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(headerInterceptor);
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse
                        .newBuilder()
                        .body(new FileResponseBody(originalResponse))
                        .build();
            }
        });
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();

    }


    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public void downLoad(String url, FileCallback fileCallback){
        apiService.loadFile(url).enqueue(fileCallback);
    }
}

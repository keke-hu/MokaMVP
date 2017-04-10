package com.cdwx.moka.net;

import android.content.Context;


import com.cdwx.moka.app.AppConstants;
import com.cdwx.moka.exception.APIException;
import com.cdwx.moka.model.ResponseBean;
import com.cdwx.moka.utils.MokaUtil;

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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 */
class RetrofitHttpUtil {
    private static final String TAG = "OkHttp";
    /**
     * 服务器地址
     */
    protected static final String BASE_URL = "https://app.api.mmote.cn/";
    //local address
//    protected static final String BASE_URL = "http://192.168.31.253/";

    APIService apiService;
    protected static Retrofit retrofit = null;
    protected static OkHttpClient okHttpClient = null;

    protected Context mContext;
    //缓存设置0不缓存
    protected boolean isUseCache;
    protected int maxCacheTime = 60;

    public void setMaxCacheTime(int maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }

    public void setUseCache(boolean useCache) {
        isUseCache = useCache;
    }

    public APIService getService() {
        if (apiService == null && retrofit != null) {
            apiService = retrofit.create(APIService.class);
        }
        return apiService;
    }

    public void init(Context context) {
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
        if (AppConstants.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(MokaUtil.getCacheDir(mContext), "httpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!MokaUtil.isNetworkConnected(mContext) || isUseCache) {//如果网络不可用或者设置只用网络
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                } else if (MokaUtil.isNetworkConnected(mContext) && !isUseCache) {//网络可用
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                }
                Response response = chain.proceed(request);
                if (MokaUtil.isNetworkConnected(mContext)) {//如果网络可用
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


    <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    class ResponseBeanFunc<T> implements Func1<ResponseBean<T>, T> {

        @Override
        public T call(ResponseBean<T> responseBean) {
            if (!responseBean.success()) {
                throw new APIException(responseBean.getState(), responseBean.getMsg());
            }
            return responseBean.data;
        }
    }


}

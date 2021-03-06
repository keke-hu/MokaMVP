package com.cdwx.moka.app;

/**
 * Created by KeKe on 2017/4/10.
 */

public class AppConstants {
    public static final boolean DEBUG=true;
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    /**
     * 网络中断，请检查您的网络状态
     */
    public static final int NETERROR = -1000;
    /**
     * 未知错误
     */
    public static final int UNKONWERROR = -1001;
    //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数。还有很多别的接口大家可以研究。
    public static final String KEY_API = "e6d6ec3ba2f9d7a3051a6c09f0524738";

    public static  final int WECHA_SEARCH = 1000;
}

package com.cdwx.moka.net;

/**
 * 请求返回接口
 */
public interface OnResponseListener<T> {
    void onSuccess(T t);
    void onError(int code, String message);
    void onCancel();
}

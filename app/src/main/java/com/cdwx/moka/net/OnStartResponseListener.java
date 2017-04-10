package com.cdwx.moka.net;

/**
 * Created by KeKe on 2017/3/30.
 *
 */

public interface OnStartResponseListener<T>{
    void onRequestStart();
    void onSuccess(T t);
    void onError(int code, String message);
    void onCancel();
}

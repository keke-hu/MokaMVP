package com.cdwx.moka.model;

import android.text.TextUtils;


/**
 * @类名: ResponseBean
 * @作者: hukeke
 */
public class ResponseBean<T> {
    private int state;//request result
    public T data;//response data
    private String msg;//callback message to view
    private double timestamp;//


    public String getMsg() {
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        return msg;
    }

    public int getState(){
        return state;
    }

    public String getData() {
        return data.toString();
    }

    public boolean success() {
        return state == 200;
    }

    public double getTimestamp() {
        return timestamp;
    }

}

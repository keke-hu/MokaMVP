package com.cdwx.moka.net;

import android.content.Context;


import com.cdwx.moka.app.AppConstants;
import com.cdwx.moka.exception.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by KeKe on 2017/3/30.
 * 包含开始的监听
 */

public class ProgressStartSubscriber <T> extends Subscriber<T> implements ProgressCancelListener {

    private OnStartResponseListener<T> mOnStartResponseListenern;
    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressStartSubscriber(OnStartResponseListener<T> mOnStartResponseListenern, Context context, boolean show) {
        this.mOnStartResponseListenern = mOnStartResponseListenern;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true, show);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if(mOnStartResponseListenern!=null){
            mOnStartResponseListenern.onRequestStart();
        }
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            if (mOnStartResponseListenern != null) {
                mOnStartResponseListenern.onError(AppConstants.NETERROR, "网络中断，请检查您的网络状态");
            }
        } else if (e instanceof ConnectException) {
            if (mOnStartResponseListenern != null) {
                mOnStartResponseListenern.onError(AppConstants.NETERROR, "网络中断，请检查您的网络状态");
            }
        } else if (e instanceof APIException) {
            if (mOnStartResponseListenern != null) {
                mOnStartResponseListenern.onError(((APIException) e).getCode(), e.getMessage());
            }
        } else {
            if (mOnStartResponseListenern != null) {
                mOnStartResponseListenern.onError(AppConstants.UNKONWERROR, e.getMessage());
            }
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mOnStartResponseListenern != null) {
            mOnStartResponseListenern.onSuccess(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
            mOnStartResponseListenern.onCancel();
        }
    }
}

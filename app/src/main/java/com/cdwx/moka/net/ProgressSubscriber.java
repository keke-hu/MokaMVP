package com.cdwx.moka.net;

import android.content.Context;

import com.mmote.mmote.constants.MmoteConstants;
import com.mmote.mmote.exception.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by hjzhang on 16/7/26.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private OnResponseListener<T> mOnResponseListener;
    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressSubscriber(OnResponseListener<T> mOnResponseListener, Context context, boolean show) {
        this.mOnResponseListener = mOnResponseListener;
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
            if (mOnResponseListener != null) {
                mOnResponseListener.onError(MmoteConstants.NETERROR, "网络中断，请检查您的网络状态");
            }
        } else if (e instanceof ConnectException) {
            if (mOnResponseListener != null) {
                mOnResponseListener.onError(MmoteConstants.NETERROR, "网络中断，请检查您的网络状态");
            }
        } else if (e instanceof APIException) {
            if (mOnResponseListener != null) {
                mOnResponseListener.onError(((APIException) e).getCode(), e.getMessage());
            }
        } else {
            if (mOnResponseListener != null) {
                mOnResponseListener.onError(MmoteConstants.UNKONWERROR, e.getMessage());
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
        if (mOnResponseListener != null) {
            mOnResponseListener.onSuccess(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
            mOnResponseListener.onCancel();
        }
    }
}
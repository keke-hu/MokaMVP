package com.cdwx.moka.presenter.impl;

import com.cdwx.moka.app.App;
import com.cdwx.moka.model.MainInfoBean;
import com.cdwx.moka.net.OnResponseListener;
import com.cdwx.moka.net.ProgressSubscriber;
import com.cdwx.moka.presenter.BasePresenter;
import com.cdwx.moka.presenter.MainFragmentPresenter;
import com.cdwx.moka.utils.APIServiceUtils;

import javax.inject.Inject;

/**
 * Created by KeKe on 2017/4/11.
 */

public class MainFragmentPresenterImpl extends BasePresenter<MainFragmentPresenter.View>
        implements MainFragmentPresenter.Presenter {

    private APIServiceUtils apiServiceUtils;

    @Inject
    public MainFragmentPresenterImpl(APIServiceUtils apiServiceUtils) {
        this.apiServiceUtils = apiServiceUtils;
    }

    @Override
    public void fetchMainInfoData() {
        invoke(apiServiceUtils.fetchMainInfoData(), new ProgressSubscriber<MainInfoBean>(new OnResponseListener<MainInfoBean>() {
            @Override
            public void onSuccess(MainInfoBean mainInfoBean) {
                mLifeSubscription.refreshView(mainInfoBean);
            }

            @Override
            public void onError(int code, String message) {

            }

            @Override
            public void onCancel() {

            }
        }, App.getInstance(), true));
    }
}

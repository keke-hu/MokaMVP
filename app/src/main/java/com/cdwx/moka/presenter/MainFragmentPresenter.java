package com.cdwx.moka.presenter;

import com.cdwx.moka.model.MainInfoBean;
import com.cdwx.moka.net.LifeSubscription;

/**
 * Created by KeKe on 2017/4/11.
 */

public interface MainFragmentPresenter {

    interface View extends LifeSubscription {
        void refreshView(MainInfoBean mainInfoBean);
    }

    interface Presenter {
        void fetchMainInfoData();
    }
}

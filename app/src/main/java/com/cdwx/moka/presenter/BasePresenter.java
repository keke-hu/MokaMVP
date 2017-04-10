package com.cdwx.moka.presenter;


import com.cdwx.moka.app.AppConstants;
import com.cdwx.moka.net.LifeSubscription;
import com.cdwx.moka.net.Stateful;

import java.util.List;

import rx.Observable;

/**
 * Created by quantan.liu on 2017/3/22.
 */

public class BasePresenter<T extends LifeSubscription> {

    protected T mLifeSubscription;

    public void setLifeSubscription(LifeSubscription mLifeSubscription) {
        this.mLifeSubscription = (T) mLifeSubscription;
    }


    /**
     * 给子类检查返回集合是否为空
     * 这样子做虽然耦合度高，但是接口都不是统一定的，我们没有什么更好的办法
     * @param list
     */
    public void checkState(List list) {
        if (list.size() == 0) {
            if (mLifeSubscription instanceof Stateful)
                ((Stateful) mLifeSubscription).setState(AppConstants.STATE_EMPTY);
            return;
        }
    }
}

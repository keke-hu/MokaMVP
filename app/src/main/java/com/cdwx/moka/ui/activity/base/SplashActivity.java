package com.cdwx.moka.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;

import com.cdwx.moka.R;
import com.cdwx.moka.ui.activity.main.NavigationActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by KeKe on 2017/4/11.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        startActivity(new Intent(mContext, NavigationActivity.class));
                        finish();
                        return null;
                    }
                })
                .subscribe();
    }
}

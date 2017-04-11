package com.cdwx.moka.injector.component;

import android.app.Activity;


import com.cdwx.moka.injector.moudule.ActivityModule;
import com.cdwx.moka.injector.scope.ActivityScope;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}

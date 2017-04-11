package com.cdwx.moka.injector.component;


import com.cdwx.moka.app.App;
import com.cdwx.moka.injector.moudule.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
}

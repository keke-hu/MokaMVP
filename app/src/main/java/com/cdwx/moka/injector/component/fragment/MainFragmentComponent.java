package com.cdwx.moka.injector.component.fragment;

import com.cdwx.moka.injector.moudule.net.RequestFactoryModule;
import com.cdwx.moka.ui.fragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KeKe on 2017/4/11.
 */
@Singleton
@Component(modules = { RequestFactoryModule.class})
public interface MainFragmentComponent {
    void inject(MainFragment mainFragment);
}

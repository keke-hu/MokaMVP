package com.cdwx.moka.injector.component;

import android.app.Activity;


import com.cdwx.moka.injector.moudule.FragmentModule;
import com.cdwx.moka.injector.scope.FragmentScope;

import dagger.Component;

/**
 * Created by quantan.liu on 2017/3/21.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

}

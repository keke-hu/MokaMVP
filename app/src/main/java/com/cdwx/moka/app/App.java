package com.cdwx.moka.app;

import android.app.Application;

/**
 * Created by KeKe on 2017/4/10.
 */

public class App extends Application {
    private static App app;
    public static String deviceID = "";

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}

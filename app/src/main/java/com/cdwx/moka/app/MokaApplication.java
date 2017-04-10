package com.cdwx.moka.app;

import android.app.Application;

/**
 * Created by KeKe on 2017/4/10.
 */

public class MokaApplication extends Application {
    private static MokaApplication app;
    public static String deviceID = "";

    public static MokaApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}

package com.cdwx.moka.utils;


import com.cdwx.moka.app.App;

public class UserInfoHelper {
    private static final UserInfoHelper instance = new UserInfoHelper();
    PreferencesHelper mHelper;
    private String userId = "userId";


    public static UserInfoHelper getInstance() {
        return instance;
    }

    private UserInfoHelper() {
        mHelper = new PreferencesHelper(App.getInstance());
    }

    public String getUserId() {
        return mHelper.getValue(userId, "");
    }

    public void login(String userId) {
        mHelper.setValue(this.userId, userId);
    }

    public void loginOut() {
        mHelper.setValue(this.userId, "");
    }


}

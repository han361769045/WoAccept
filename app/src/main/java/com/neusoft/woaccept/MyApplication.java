package com.neusoft.woaccept;

import android.app.Application;

import com.neusoft.woaccept.model.ResLogin;

import org.androidannotations.annotations.EApplication;

/**
 * Created by Leo on 2016/4/27.
 */
@EApplication
public class MyApplication extends Application {

    private ResLogin mResLogin;


    public ResLogin getResLogin() {
        return mResLogin;
    }

    public void setResLogin(ResLogin resLogin) {
        mResLogin = resLogin;
    }
}

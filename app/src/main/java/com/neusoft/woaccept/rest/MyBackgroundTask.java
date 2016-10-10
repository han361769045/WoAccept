package com.neusoft.woaccept.rest;

import android.content.Context;

import com.neusoft.woaccept.tools.AndroidTool;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

/**
 * Created by LeoLu on 2016/10/10.
 */
@EBean
public class MyBackgroundTask {

    @RootContext
    Context context;

    @UiThread
    public void showLoading() {
        AndroidTool.showLoadDialog(context);
    }

    @UiThread(delay = 300)
    public void dismissLoading() {
        AndroidTool.dismissdialog(context);
    }

}

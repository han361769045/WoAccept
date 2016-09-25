package com.neusoft.woaccept.rest;

import android.content.Context;
import android.util.Log;

import com.neusoft.woaccept.tools.AndroidTool;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by leo on 2015/6/2.
 */
@EBean
public class MyInterceptor implements ClientHttpRequestInterceptor {

    @RootContext
    Context context;

//    @StringRes
//    String no_net;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data,
                                        ClientHttpRequestExecution execution) throws IOException {
        String s = new String(data);
        Log.e(context.getPackageName(), s);
        showLoading();
        return execution.execute(request, data);
    }

    @UiThread
    void showLoading() {
        AndroidTool.showLoadDialog(context);
    }

}

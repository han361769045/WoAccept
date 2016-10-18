package ${packageName}.rest;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;


@EBean
public class MyOkHttpClientHttpRequestFactory extends OkHttpClientHttpRequestFactory {


    @RootContext
    Context context;

    @AfterInject
    void afterInject() {
        this.setConnectTimeout(30 * 1000);
        this.setReadTimeout(30 * 1000);
        this.setWriteTimeout(30 * 1000);
    }

}

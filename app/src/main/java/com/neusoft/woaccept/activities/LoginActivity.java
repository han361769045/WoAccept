package com.neusoft.woaccept.activities;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.neusoft.woaccept.R;
import com.neusoft.woaccept.model.ReqLogin;
import com.neusoft.woaccept.model.ReqSendCode;
import com.neusoft.woaccept.model.ResLogin;
import com.neusoft.woaccept.model.ResLoginSms;
import com.neusoft.woaccept.rest.MyErrorHandler;
import com.neusoft.woaccept.rest.MyRestClient;
import com.neusoft.woaccept.tools.AndroidTool;
import com.neusoft.woaccept.tools.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by LeoLu on 2016/9/22.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById
    EditText account, password, sms;

    @ViewById
    Button login_sign_in_button;

    @StringRes
    String login_user_fail, login_pwd_fail, login_sms_fail, login_pwd_short, login_sms_tip;

    @RestService
    MyRestClient myRestClient;

    @Bean
    MyErrorHandler myErrorHandler;

    String sessionId;

    @AfterInject
    void afterInject() {
        myRestClient.setRestErrorHandler(myErrorHandler);
    }

    @AfterViews
    void afterView() {
        login_sign_in_button.setEnabled(false);
    }

    @AfterTextChange(value = {R.id.account, R.id.password, R.id.sms})
    void afterTextChange() {
        login_sign_in_button.setEnabled(AndroidTool.checkEditText(account, password, sms));
    }

    @Click
    void login_sign_in_button() {
        account.setError(null);
        password.setError(null);
        sms.setError(null);
        if (AndroidTool.checkIsNull(account)) {
            account.setError(login_user_fail);
            account.requestFocus();
        } else if (AndroidTool.checkIsNull(password)) {
            password.setError(login_pwd_fail);
            password.requestFocus();
        } else if (AndroidTool.checkIsNull(sms)) {
            sms.setError(login_sms_fail);
            sms.requestFocus();
        } else {
            login();
        }
    }

    @Background
    void login() {
        ReqLogin reqLogin = new ReqLogin();
        reqLogin.setAction(Constants.LOGIN_ACTION);
        reqLogin.setUsername(account.getText().toString().trim());
        reqLogin.setPassword(password.getText().toString().trim());
        reqLogin.setCheckCode(sms.getText().toString().trim());
        reqLogin.setSessionID(sessionId);
        afterLogin(myRestClient.login(reqLogin));
    }

    @UiThread
    void afterLogin(ResLogin result) {
        if (result == null) {
            AndroidTool.showToast(this, no_net);
        } else if ("0000".equals(result.getCode())) {
            app.setResLogin(result);
            finish();
        } else {
            AndroidTool.showToast(this, result.getDetail());
        }
    }


    @Click
    void login_sms_btn() {
        account.setError(null);
        password.setError(null);
        if (AndroidTool.checkIsNull(account)) {
            account.setError(login_user_fail);
            account.requestFocus();
        } else if (AndroidTool.checkIsNull(password)) {
            password.setError(login_pwd_fail);
            password.requestFocus();
        } else {
            sendCode();
        }
    }

    @Background
    void sendCode() {
        ReqSendCode reqBaseModel = new ReqSendCode();
        reqBaseModel.setAction(Constants.IDENTIFY_ACTION);
        reqBaseModel.setUsername(account.getText().toString().trim());
        reqBaseModel.setPassword(password.getText().toString().trim());
        afterSendCode(myRestClient.sendCode(reqBaseModel));
    }

    @UiThread
    void afterSendCode(ResLoginSms result) {
        Log.e(this.getPackageName() + this.getLocalClassName(), new Gson().toJson(result));
        if (result == null) {
            AndroidTool.showToast(this, no_net);
        } else if ("0000".equals(result.getCode())) {
            sms.setText(result.getCheckCode());
            sessionId = result.getSessionID();
            AndroidTool.showToast(this, login_sms_tip);
        } else {
            AndroidTool.showToast(this, result.getDetail());
        }
    }
}

package ${packageName}.activities;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import ${packageName}.MyApplication;
import ${packageName}.R;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    @SystemService
    InputMethodManager inputMethodManager;

    @SystemService
    LayoutInflater layoutInflater;

    @StringRes
    String no_net;

    @App
    MyApplication app;

    Paint paint = new Paint();

    @AfterViews
    void baseAfterView() {
        
    }
}

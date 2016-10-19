package com.neusoft.woaccept.activities;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import com.neusoft.woaccept.MyApplication;
import com.neusoft.woaccept.tools.OSUtils;
import com.neusoft.woaccept.tools.StatueBarTools;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by Leo on 2016/4/27.
 */
@EActivity
//@HierarchyViewerSupport
public abstract class BaseActivity extends AppCompatActivity {

    @SystemService
    InputMethodManager inputMethodManager;

    @SystemService
    LayoutInflater layoutInflater;

    @App
    MyApplication app;

    @ColorRes
    int line_color;

    Paint paint = new Paint();

    @StringRes
    String no_net;

    @StringRes
    String empty_search, empty_order, empty_review, empty_no_review, empty_logistics;

    @AfterViews
    void baseAfterView() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明状态栏
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
        if (OSUtils.isMIUI()) {
            StatueBarTools.setMiuiStatusBarDarkMode(this, true);
        } else if (OSUtils.isFlyme()) {
            StatueBarTools.setMeizuStatusBarDarkIcon(this, true);
        }
    }


    public void finish() {
        closeInputMethod();
        super.finish();
    }


    //隐藏软键盘
    void closeInputMethod() {
        /*隐藏软键盘*/
        if (inputMethodManager.isActive()) {
            if (getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}

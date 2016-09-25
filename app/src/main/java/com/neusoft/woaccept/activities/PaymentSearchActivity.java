package com.neusoft.woaccept.activities;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.customview.MyTitleBar;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


/**
 * Created by LeoLu on 2016/9/14.
 */

@EActivity(R.layout.activity_payment_search)
public class PaymentSearchActivity extends BaseActivity {

    @ViewById
    MyTitleBar my_title_bar;

    @ViewById
    TextView txt_number;

    @ViewById
    EditText text_search;

    @ViewById
    LinearLayout ll_number;

    @AfterViews
    void afterView() {
        my_title_bar.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_number.isShown()) {
                    text_search.setText("");
                } else {
                    finish();
                }
            }
        });
    }

    @AfterTextChange
    void text_search(Editable s) {
        String str = s.toString().trim();
        if (str.length() > 0) {
            ll_number.setVisibility(View.VISIBLE);
            txt_number.setText(str);
        } else {
            ll_number.setVisibility(View.GONE);
        }
    }

    @Click
    void ll_number() {
        PaymentSearchResultActivity_.intent(this).phoneNumber(text_search.getText().toString().trim()).start();
    }
}

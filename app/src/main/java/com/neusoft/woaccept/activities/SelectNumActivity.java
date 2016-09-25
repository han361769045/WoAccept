package com.neusoft.woaccept.activities;

import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.adapters.SelectNumAdapter;
import com.neusoft.woaccept.items.FilterPopup;
import com.neusoft.woaccept.items.FilterPopup_;
import com.neusoft.woaccept.model.PhoneNumber;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


/**
 * Created by LeoLu on 2016/9/23.
 */
@EActivity(R.layout.activity_select_num)
public class SelectNumActivity extends BaseUltimateRecyclerViewActivity<PhoneNumber> {

    @ViewById
    RadioGroup radio_group;

    @ViewById
    RadioButton rb_number, rb_price, rb_filter;

    PopupWindow mPopupWindow;

    FilterPopup mFilterPopup;

    @Bean
    void setAdapter(SelectNumAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    @Click
    void rb_number() {

    }

    @Click
    void rb_price() {

    }

    @Click
    void rb_filter() {
        if (rb_filter.isChecked()) {
            if (mPopupWindow == null) {
                mFilterPopup = FilterPopup_.build(this);
                mPopupWindow = new PopupWindow(mFilterPopup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mFilterPopup.setPopupWindow(mPopupWindow);
            }
            mPopupWindow.showAsDropDown(rb_filter);
        }
    }

    @Subscribe
    public void notifyUI(String[] str) {
        mPopupWindow.dismiss();
    }

    @Override
    public void afterLoadMore() {

    }
}

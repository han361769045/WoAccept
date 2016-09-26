package com.neusoft.woaccept.fragments;

import android.widget.LinearLayout;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.activities.SelectNumActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EFragment(R.layout.fragment_cbss)
public class CbssFragment extends BaseFragment {

    @ViewById
    LinearLayout cbss_select_num;


    @Click
    void cbss_select_num() {
        SelectNumActivity_.intent(this).if34g("4").startForResult(1000);
    }

}

package com.neusoft.woaccept.activities;

import android.support.v4.app.Fragment;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.adapters.BaseFragmentStatePagerAdapter;
import com.neusoft.woaccept.customview.NoScrollViewPager;
import com.neusoft.woaccept.fragments.BssFragment_;
import com.neusoft.woaccept.fragments.CbssFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


/**
 * Created by LeoLu on 2016/9/30.
 */
@EActivity(R.layout.activity_entry_customer_info)
public class EntryCustomerInfoActivity extends BaseActivity {

    @ViewById
    NoScrollViewPager view_pager;

    @Bean
    BaseFragmentStatePagerAdapter mBaseFragmentStatePagerAdapter;

    Fragment[] fragments = {CbssFragment_.builder().build(), BssFragment_.builder().build()};

    @AfterViews
    void afterView() {
        view_pager.setNoScroll(true);
        mBaseFragmentStatePagerAdapter.setFragment(fragments);
        view_pager.setAdapter(mBaseFragmentStatePagerAdapter);
    }

}

package com.neusoft.woaccept.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.adapters.NewNetworkVpAdapter;
import com.neusoft.woaccept.fragments.BssFragment_;
import com.neusoft.woaccept.fragments.CbssFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EActivity(R.layout.activity_new_network)
public class NewNetworkActivity extends BaseActivity {

    @ViewById
    TabLayout tab_new_network;

    @ViewById
    ViewPager vp_new_network;

    @Bean
    NewNetworkVpAdapter myAdapter;

    Fragment[] fragments = {CbssFragment_.builder().build(), BssFragment_.builder().build()};

    @AfterViews
    void afterView() {
        myAdapter.setFragments(fragments);
        vp_new_network.setAdapter(myAdapter);
        tab_new_network.setupWithViewPager(vp_new_network);
    }

}

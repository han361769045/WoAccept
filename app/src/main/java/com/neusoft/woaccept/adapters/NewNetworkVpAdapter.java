package com.neusoft.woaccept.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringArrayRes;

/**
 * Created by zly on 2016/9/12.
 */
@EBean
public class NewNetworkVpAdapter extends BaseFragmentPagerAdapter {

    Fragment[] fragments;

    @StringArrayRes
    String[] vp_titles;

    public NewNetworkVpAdapter(Context context) {
        super(context);
    }

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }


    public Fragment getItem(int position) {
        return fragments.length == 0 ? null : fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length == 0 ? 0 : fragments.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return vp_titles[position];
    }

}

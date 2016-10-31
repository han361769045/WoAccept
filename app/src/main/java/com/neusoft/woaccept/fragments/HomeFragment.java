package com.neusoft.woaccept.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.leo.lu.bannerauto.BannerLayout;
import com.leo.lu.bannerauto.bannertypes.DefaultBannerView;
import com.neusoft.woaccept.MyApplication;
import com.neusoft.woaccept.R;
import com.neusoft.woaccept.activities.NewNetworkActivity_;
import com.neusoft.woaccept.activities.PaymentActivity_;
import com.neusoft.woaccept.adapters.BaseRecyclerViewAdapter;
import com.neusoft.woaccept.adapters.WorkMenuAdapter;
import com.neusoft.woaccept.model.WorkMenuBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by LeoLu on 2016/9/22.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseRecyclerViewFragment<WorkMenuBean> {

    @ViewById
    BannerLayout slider;

    @App
    MyApplication app;

    @Bean
    void setAdapter(WorkMenuAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    @AfterViews
    void afterView() {
        for (int i = 0; i < 3; i++) {
            DefaultBannerView defaultBannerView = new DefaultBannerView(getActivity());
            defaultBannerView.image(R.drawable.home_ad);
            Bundle bundle = new Bundle();
            defaultBannerView.bundle(bundle);
            defaultBannerView.setScaleType(DefaultBannerView.ScaleType.Fit);
            slider.addBanner(defaultBannerView);
        }
        horizontalItem(4);
        myAdapter.getMoreData(app.getResLogin().getWorkMenu());

        myAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<WorkMenuBean>() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, WorkMenuBean obj, int position) {
                switch (obj.getMenuId()) {
                    //新装入网
                    case "pre_m":
                        NewNetworkActivity_.intent(HomeFragment.this).start();
                        break;
                    //现金缴费
                    case "pre_g":
                        PaymentActivity_.intent(HomeFragment.this).start();
                        break;
                    //订购流量包
                    case "pre_d":
                        break;
                    //缴费返销
                    case "pre_ab":
                        break;
                    //宽带入网
                    case "pre_i":
                        break;
                    //预登录返单
                    case "pre_lmx2":
                        break;
                }
            }
        });

        myAdapter.setOnItemLongClickListener(new BaseRecyclerViewAdapter.OnItemLongClickListener<WorkMenuBean>() {
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder, WorkMenuBean obj, int position) {

            }
        });

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            slider.stopAutoCycle();
        } else {
            slider.startAutoCycle();
        }
    }
}

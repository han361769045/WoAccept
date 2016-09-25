package com.neusoft.woaccept.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.neusoft.woaccept.items.CustomerInfoItemView_;
import com.neusoft.woaccept.model.CustomerInfo;

import org.androidannotations.annotations.EBean;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EBean
public class CustomerInfoAdapter extends BaseRecyclerViewAdapter<CustomerInfo> {


    @Override
    public void getMoreData(Object... objects) {

    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return CustomerInfoItemView_.build(context);
    }
}

package com.neusoft.woaccept.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.neusoft.woaccept.items.SelectNumItemView_;
import com.neusoft.woaccept.model.PhoneNumber;

import org.androidannotations.annotations.EBean;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EBean
public class SelectNumAdapter extends BaseUltimateRecyclerViewAdapter<PhoneNumber> {


    @Override
    public void getMoreData(int pageIndex, int pageSize, boolean isRefresh, Object... objects) {

    }

    @Override
    protected View onCreateItemView(ViewGroup parent) {
        return SelectNumItemView_.build(context);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}

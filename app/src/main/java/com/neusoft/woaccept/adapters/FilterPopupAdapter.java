package com.neusoft.woaccept.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.neusoft.woaccept.items.FilterPopupItemView_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoLu on 2016/9/25.
 */

@EBean
public class FilterPopupAdapter extends BaseRecyclerViewAdapter<String[]> {


    @Override
    public void getMoreData(Object... objects) {
        List<String[]> items = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String[] temp = new String[2];
            temp[0] = "一类靓号";
            temp[0] = String.valueOf(i * 100);
            items.add(temp);
        }
//        items.

    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return FilterPopupItemView_.build(context);
    }


}

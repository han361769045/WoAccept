package com.neusoft.woaccept.items;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.adapters.BaseRecyclerViewAdapter;
import com.neusoft.woaccept.adapters.FilterPopupAdapter;
import com.neusoft.woaccept.listener.OttoBus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

/**
 * Created by LeoLu on 2016/9/25.
 */
@EViewGroup(R.layout.filter_popup)
public class FilterPopup extends LinearLayout {

    @Bean
    OttoBus bus;

    @ViewById
    RecyclerView recyclerView;

    GridLayoutManager mGridLayoutManager;

    @Bean
    FilterPopupAdapter myAdapter;

    PopupWindow mPopupWindow;

    public FilterPopup(Context context) {
        super(context);
        mGridLayoutManager = new GridLayoutManager(context, 3);
    }

    @AfterViews
    void afterView() {
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(mGridLayoutManager);
        myAdapter.getMoreData();
        myAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String[]>() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, String[] obj, int position) {
                bus.post(obj);
            }
        });
    }

    public void setPopupWindow(PopupWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }


    @Touch
    void recyclerView(View v, MotionEvent event) {
        int height = v.getHeight();
        int flHeight = recyclerView.getHeight();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (y < height - flHeight && mPopupWindow != null) {
                mPopupWindow.dismiss();
            }
        }
    }
}

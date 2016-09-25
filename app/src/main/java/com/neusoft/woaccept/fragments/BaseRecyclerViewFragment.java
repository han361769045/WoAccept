package com.neusoft.woaccept.fragments;

import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leo.lu.hfrefreshrecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;
import com.neusoft.woaccept.adapters.BaseRecyclerViewAdapter;
import com.neusoft.woaccept.customview.MyTitleBar;
import com.neusoft.woaccept.rest.MyErrorHandler;
import com.neusoft.woaccept.tools.AndroidTool;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


/**
 * Created by Leo on 2016/5/21.
 */
@EFragment(resName = "activity_recycler_view")
public abstract class BaseRecyclerViewFragment<T> extends BaseFragment {

    @ViewById
    MyTitleBar myTitleBar;

    @ViewById
    RecyclerView recyclerView;

    @Bean
    MyErrorHandler myErrorHandler;

    BaseRecyclerViewAdapter<T> myAdapter;

    GridLayoutManager gridLayoutManager;

    LinearLayoutManager linearLayoutManager;

    Paint paint = new Paint();

    @AfterViews
    void afterRecyclerView() {
        AndroidTool.showLoadDialog(this);
        recyclerView.setHasFixedSize(true);
        paint.setStrokeWidth(1);
        paint.setColor(line_color);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).margin(35).paint(paint).build());
    }


    //线性布局
    void verticalItem() {
        if (linearLayoutManager == null)
            linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(null);
        myAdapter.verticalAndHorizontal = BaseRecyclerViewAdapter.VerticalAndHorizontal.Vertical;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    //网格布局
    void horizontalItem(int spanCount) {
        if (gridLayoutManager == null)
            gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myAdapter);
        myAdapter.verticalAndHorizontal = BaseRecyclerViewAdapter.VerticalAndHorizontal.Horizontal;
    }

}

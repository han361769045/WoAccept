package com.neusoft.woaccept.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leo.lu.llrecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;
import com.neusoft.woaccept.adapters.BaseRecyclerViewAdapter;
import com.neusoft.woaccept.customview.MyTitleBar;
import com.neusoft.woaccept.rest.MyErrorHandler;
import com.neusoft.woaccept.rest.MyRestClient;
import com.neusoft.woaccept.tools.AndroidTool;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by Leo on 2016/5/21.
 */
@EActivity(resName = "activity_recycler_view")
public abstract class BaseRecyclerViewActivity<T> extends BaseActivity {

    @ViewById
    MyTitleBar myTitleBar;

    @ViewById
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;

    BaseRecyclerViewAdapter<T> myAdapter;

    @Bean
    MyErrorHandler myErrorHandler;

    @RestService
    MyRestClient myRestClient;


    @AfterInject
    void afterInject() {
        myRestClient.setRestErrorHandler(myErrorHandler);
    }


    @AfterViews
    public void afterRecyclerView() {
        AndroidTool.showLoadDialog(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
        paint.setStrokeWidth(1);
        paint.setColor(line_color);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).margin(0).paint(paint).build());
    }


}

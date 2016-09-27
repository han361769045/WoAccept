package com.neusoft.woaccept.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.adapters.BaseUltimateRecyclerViewAdapter;
import com.neusoft.woaccept.adapters.SelectNumAdapter;
import com.neusoft.woaccept.items.FilterPopup;
import com.neusoft.woaccept.items.FilterPopup_;
import com.neusoft.woaccept.model.Msg;
import com.neusoft.woaccept.model.PhoneNumber;
import com.neusoft.woaccept.model.PhoneNumberType;
import com.neusoft.woaccept.model.QueryParasEntity;
import com.neusoft.woaccept.model.ReqBaseModel;
import com.neusoft.woaccept.model.ReqSelectPhoneNumber;
import com.neusoft.woaccept.tools.Constants;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by LeoLu on 2016/9/23.
 */
@EActivity(R.layout.activity_select_num)
public class SelectNumActivity extends BaseUltimateRecyclerViewActivity<PhoneNumber> {

    @ViewById
    RadioGroup radio_group;

    @ViewById
    RadioButton rb_number, rb_price, rb_filter;

    @DrawableRes
    Drawable popBackground;

    String if34g, channelId, channelType, operatorId, qryCbss, queryType, queryPara;

    String type, sort;

    PopupWindow mPopupWindow;

    FilterPopup mFilterPopup;

    boolean isSelected;

    ReqBaseModel<Msg<ReqSelectPhoneNumber>> model;

    @Bean
    void setAdapter(SelectNumAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    @Extra
    void if34g(String if34g) {
        this.if34g = if34g;
        if ("4".equals(if34g)) {
            qryCbss = "1";
            channelId = app.getResLogin().getChannelId();
            channelType = app.getResLogin().getChannelType();
            operatorId = app.getResLogin().getOperatorId_cb();
        } else {
            qryCbss = "0";
            channelId = app.getResLogin().getChannelId_bss();
            channelType = app.getResLogin().getChannelType_bss();
            operatorId = app.getResLogin().getOperatorId_bs();
        }
        queryType = "04";
        queryPara = "6";
        type = "0";
        sort = "asc";
        model = new ReqBaseModel<>();
        Msg<ReqSelectPhoneNumber> msg = new Msg<>();
        ReqSelectPhoneNumber phoneNumber = new ReqSelectPhoneNumber();
        model.setAction(Constants.SERVICE_IDQRY_ACTION);
        model.setSessionID(app.getResLogin().getSessionID());
        model.setApplyevent("301");
        model.setNet_type_code("50");
        model.setIf34g(if34g);
        model.setReq(msg);
        phoneNumber.setChannelId(channelId);
        phoneNumber.setOperatorId(operatorId);
        phoneNumber.setChannelType(channelType);
        phoneNumber.setCity(app.getResLogin().getCity());
        phoneNumber.setDistrict(app.getResLogin().getDistrict());
        phoneNumber.setProvince(app.getResLogin().getProvince());
        phoneNumber.setIs3G("");
        phoneNumber.setGroupFlag("3");
        phoneNumber.setBusType("1");
        phoneNumber.setQryCbss("1");
        phoneNumber.setSerType("1");
        QueryParasEntity queryParasEntity = new QueryParasEntity();
        queryParasEntity.setQueryType(queryType);
        queryParasEntity.setQueryPara(queryPara);
        List<QueryParasEntity> list = new ArrayList<>(1);
        list.add(queryParasEntity);
        phoneNumber.setQueryParas(list);
        msg.setMsg(phoneNumber);
    }

    @AfterViews
    void afterView() {
        rb_number.setSelected(true);
        myAdapter.setOnItemClickListener(new BaseUltimateRecyclerViewAdapter.OnItemClickListener<PhoneNumber>() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, PhoneNumber obj, int position) {
                Intent intent = new Intent();
                intent.putExtra("phoneNumber", obj);
                setResult(1001, intent);
                finish();
            }

            @Override
            public void onHeaderClick(RecyclerView.ViewHolder viewHolder, int position) {

            }
        });

    }


    public void setLayoutManager() {
        horizontalItem();
        setVerticalDividerItemDecoration(0, 0);
    }

    @Click
    void rb_number() {
        type = "0";
        if (rb_number.isChecked() && isSelected) {
            sort = "desc";
            isSelected = false;
            rb_number.setSelected(false);
            Collections.reverse(myAdapter.getItems());
            myAdapter.notifyDataSetChanged();
        } else if (rb_number.isChecked()) {
            sort = "asc";
            isSelected = true;
            rb_number.setSelected(true);
            Collections.sort(myAdapter.getItems());
            myAdapter.notifyDataSetChanged();
        }
        if (mPopupWindow != null && mPopupWindow.isShowing())
            mPopupWindow.dismiss();
    }

    @CheckedChange
    void rb_number(boolean isChecked) {
        if (!isChecked) {
            rb_number.setSelected(false);
        } else {
            isSelected = false;
        }
    }

    @CheckedChange
    void rb_price(boolean isChecked) {
        if (!isChecked) {
            rb_price.setSelected(false);
        } else {
            isSelected = false;
        }
    }

    @Click
    void rb_price() {
        type = "1";
        if (rb_price.isChecked() && isSelected) {
            sort = "desc";
            rb_price.setSelected(false);
            isSelected = false;
            Collections.sort(myAdapter.getItems(), new Comparator<PhoneNumber>() {
                @Override
                public int compare(PhoneNumber o1, PhoneNumber o2) {
                    return o1.getAdvancePay().compareTo(o2.getAdvancePay());
                }
            });
            myAdapter.notifyDataSetChanged();
        } else if (rb_price.isChecked()) {
            sort = "asc";
            rb_price.setSelected(true);
            isSelected = true;
            Collections.reverse(myAdapter.getItems());
            myAdapter.notifyDataSetChanged();
        }
        if (mPopupWindow != null && mPopupWindow.isShowing())
            mPopupWindow.dismiss();
    }

    @Click
    void rb_filter() {
        if (rb_filter.isChecked()) {
            closeInputMethod();
            if (mPopupWindow == null) {
                mFilterPopup = FilterPopup_.build(this);
                mPopupWindow = new PopupWindow(mFilterPopup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                mFilterPopup.setPopupWindow(mPopupWindow);
                mPopupWindow.setBackgroundDrawable(popBackground);
            }
            mPopupWindow.showAsDropDown(rb_filter);
        }
    }

    @Subscribe
    public void notifyUI(PhoneNumberType str) {
        mPopupWindow.dismiss();
        queryPara = str.getQueryPara();
        isRefresh = true;
        afterLoadMore();
    }

    @Override
    public void afterLoadMore() {
        model.getReq().getMsg().getQueryParas().get(0).setQueryPara(queryPara);
        myAdapter.getMoreData(pageIndex, 10, isRefresh, model, type, sort);
    }
}

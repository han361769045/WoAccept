package com.neusoft.woaccept.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.neusoft.woaccept.items.SelectNumItemView_;
import com.neusoft.woaccept.model.Msg;
import com.neusoft.woaccept.model.PagerResult;
import com.neusoft.woaccept.model.PhoneNumber;
import com.neusoft.woaccept.model.ReqBaseModel;
import com.neusoft.woaccept.model.ReqSelectPhoneNumber;
import com.neusoft.woaccept.model.ResBaseModel;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EBean
public class SelectNumAdapter extends BaseUltimateRecyclerViewAdapter<PhoneNumber> {

    @Override
    public void getMoreData(int pageIndex, int pageSize, boolean isRefresh, Object... objects) {
        this.isRefresh = isRefresh;
        ResBaseModel<List<PhoneNumber>> list = myRestClient.serviceidqry((ReqBaseModel<Msg<ReqSelectPhoneNumber>>) objects[0]);
        ResBaseModel<PagerResult<PhoneNumber>> result = new ResBaseModel<>();
        PagerResult<PhoneNumber> pr = new PagerResult<>();
        if (list != null && list.getNumInfo() != null) {
            pr.ListData = list.getNumInfo();
            pr.PageIndex = pageIndex;
            pr.RowCount = pr.ListData.size();
            result.setCode(list.getCode());
            result.setDetail(list.getDetail());
        } else if (list != null) {
            pr.ListData = new ArrayList<>();
        }
        result.setNumInfo(pr);
        afterGetMoreData(result);
    }

    @Override
    protected View onCreateItemView(ViewGroup parent) {
        return SelectNumItemView_.build(context);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

}

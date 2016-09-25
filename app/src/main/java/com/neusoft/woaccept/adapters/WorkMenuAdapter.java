package com.neusoft.woaccept.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.neusoft.woaccept.items.WorkMenuItemView_;
import com.neusoft.woaccept.model.BaseModelJson;
import com.neusoft.woaccept.model.WorkMenuBean;

import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EBean
public class WorkMenuAdapter extends BaseRecyclerViewAdapter<WorkMenuBean> {


    @Override
    public void getMoreData(Object... objects) {
        BaseModelJson<List<WorkMenuBean>> bmj = new BaseModelJson<>();
        bmj.Data = (List<WorkMenuBean>) objects[0];
        bmj.Successful = true;
        afterGetMoreData(bmj);
    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return WorkMenuItemView_.build(context);
    }
}

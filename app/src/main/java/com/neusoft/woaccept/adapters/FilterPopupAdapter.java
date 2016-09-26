package com.neusoft.woaccept.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.neusoft.woaccept.items.FilterPopupItemView_;
import com.neusoft.woaccept.model.BaseModelJson;
import com.neusoft.woaccept.model.PhoneNumberType;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoLu on 2016/9/25.
 */

@EBean
public class FilterPopupAdapter extends BaseRecyclerViewAdapter<PhoneNumberType> {


    @Override
    public void getMoreData(Object... objects) {
        List<PhoneNumberType> items = new ArrayList<>();
        PhoneNumberType temp1 = new PhoneNumberType("04", "0", "一类靓号", "2000");
        PhoneNumberType temp2 = new PhoneNumberType("04", "1", "二类靓号", "1000");
        PhoneNumberType temp3 = new PhoneNumberType("04", "2", "三类靓号", "600");
        PhoneNumberType temp4 = new PhoneNumberType("04", "3", "四类靓号", "300");
        PhoneNumberType temp5 = new PhoneNumberType("04", "4", "五类靓号", "100");
        PhoneNumberType temp6 = new PhoneNumberType("04", "5", "六类靓号", "50");
        PhoneNumberType temp = new PhoneNumberType("04", "6", "普通", "0");
        items.add(temp1);
        items.add(temp2);
        items.add(temp3);
        items.add(temp4);
        items.add(temp5);
        items.add(temp6);
        items.add(temp);
        BaseModelJson<List<PhoneNumberType>> result = new BaseModelJson<>();
        result.Data = items;
        result.Successful = true;
        afterGetMoreData(result);
    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return FilterPopupItemView_.build(context);
    }


}

package com.neusoft.woaccept.items;

import android.content.Context;
import android.widget.TextView;

import com.neusoft.woaccept.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by LeoLu on 2016/9/25.
 */
@EViewGroup(R.layout.filter_popup_item)
public class FilterPopupItemView extends ItemView<String[]> {

    @ViewById
    TextView txt_type_one, txt_min_cost;

    @StringRes
    String min_cost;

    public FilterPopupItemView(Context context) {
        super(context);
    }

    @Override
    protected void init(Object... objects) {
        txt_type_one.setText(_data[0]);
        txt_min_cost.setText(String.format(min_cost, _data[1]));
    }


    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}

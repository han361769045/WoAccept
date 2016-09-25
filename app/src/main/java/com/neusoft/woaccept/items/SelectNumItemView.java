package com.neusoft.woaccept.items;

import android.content.Context;
import android.widget.TextView;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.model.PhoneNumber;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EViewGroup(R.layout.activity_phone_number_item)
public class SelectNumItemView extends ItemView<PhoneNumber> {

    @ViewById
    TextView item_tv_num, item_tv_pre_save;

    @StringRes
    String pre_cost;

    public SelectNumItemView(Context context) {
        super(context);
    }

    @Override
    protected void init(Object... objects) {
        item_tv_num.setText(_data.getNumId());
        if (_data.getAdvancePay().length() - 3 > 0) {
            item_tv_pre_save.setText(String.format(pre_cost, _data.getAdvancePay().substring(0, _data.getAdvancePay().length() - 3)));
        } else {
            item_tv_pre_save.setText(String.format(pre_cost, _data.getAdvancePay()));
        }
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}

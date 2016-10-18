package com.neusoft.woaccept.items;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.activities.PaymentSearchResultActivity;
import com.neusoft.woaccept.model.CustomerInfo;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


/**
 * Created by LeoLu on 2016/9/15.
 */
@EViewGroup(R.layout.activity_payment_customer_info_item)
public class CustomerInfoItemView extends ItemView<CustomerInfo> {

    @ViewById
    CheckBox checkBox;

    @ViewById
    TextView custName, certType, certCode;

    PaymentSearchResultActivity mPaymentSearchResultActivity;

    public CustomerInfoItemView(Context context) {
        super(context);
        mPaymentSearchResultActivity = (PaymentSearchResultActivity) context;
    }

    @Override
    protected void init(Object... objects) {
        custName.setText(_data.getCustName());
        certType.setText(_data.getCertType());
        certCode.setText(_data.getCertCode());
        checkBox.setChecked(_data.isChecked());
    }

    @Click
    void checkBox() {
        if (!_data.isChecked()) {
            for (CustomerInfo ci : baseRecyclerViewAdapter.getItems()) {
                ci.setChecked(false);
                checkBox.setChecked(false);
            }
            checkBox.setChecked(true);
            _data.setChecked(true);
            baseRecyclerViewAdapter.notifyDataSetChanged();
        }
        mPaymentSearchResultActivity.changeStatue(_data);
    }
}

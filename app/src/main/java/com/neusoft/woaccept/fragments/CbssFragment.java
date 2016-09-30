package com.neusoft.woaccept.fragments;

import android.app.Activity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.activities.SelectNumActivity_;
import com.neusoft.woaccept.customview.ClearEditText;
import com.neusoft.woaccept.model.PhoneNumber;
import com.neusoft.woaccept.tools.AndroidTool;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.springframework.util.StringUtils;


/**
 * Created by LeoLu on 2016/9/23.
 */
@EFragment(R.layout.fragment_cbss)
public class CbssFragment extends BaseFragment {

    @ViewById
    LinearLayout cbss_select_num, cbss_edit_card_num;

    @ViewById
    RelativeLayout cbss_num_is_selected;

    @ViewById
    SwitchCompat switch_cbss_white_card;

    @ViewById
    TextView tv_cbss_selected_num;

    @ViewById
    ClearEditText et_cbss_input_num;

    @ViewById
    Button bt_cbss_next_step;

    @Click(value = {R.id.cbss_select_num, R.id.cbss_num_is_selected})
    void cbss_select_num() {
        SelectNumActivity_.intent(this).if34g("4").startForResult(1000);
    }

    @OnActivityResult(1000)
    void afterSelectNum(int resultCode, @OnActivityResult.Extra PhoneNumber phoneNumber) {
        if (resultCode == Activity.RESULT_OK) {
            cbss_select_num.setVisibility(View.GONE);
            cbss_num_is_selected.setVisibility(View.VISIBLE);
            tv_cbss_selected_num.setText(phoneNumber.getNumId());
            changeStatue();
        }
    }

    @CheckedChange
    void switch_cbss_white_card(boolean isChecked) {
        if (isChecked) {
            cbss_edit_card_num.setVisibility(View.GONE);
        } else {
            cbss_edit_card_num.setVisibility(View.VISIBLE);
        }
        changeStatue();
    }

    @AfterTextChange
    void et_cbss_input_num() {
        changeStatue();
    }

    void changeStatue() {
        bt_cbss_next_step.setEnabled(cbss_num_is_selected.isShown()
                && (switch_cbss_white_card.isChecked()
                || !StringUtils.isEmpty(et_cbss_input_num.getText().toString().trim())));
    }

    @Click
    void bt_cbss_next_step() {
        AndroidTool.showToast(this, "ssssssssssssss");
    }

}

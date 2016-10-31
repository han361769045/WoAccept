package com.neusoft.woaccept.activities;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neusoft.woaccept.R;

import org.androidannotations.annotations.EActivity;


/**
 * Created by LeoLu on 2016/10/26.
 */
@EActivity(R.layout.activity_maintenance_4g)
public class Maintenance4GActivity extends BaseActivity {
    TextView txt_maintenance_type;
    RelativeLayout rl_maintenance_type;
    EditText txt_maintenance_service_code, txt_maintenance_phone;
    Button btn_submit;
    BottomSheetDialog bottomSheetDialog;
    CheckBox cb_type_one, cb_type_two;
    BottomSheetBehavior<FrameLayout> bottomSheetBehavior;
    //农村渠道:1 非农村渠道:2
    private String channelType4g = "1";

}

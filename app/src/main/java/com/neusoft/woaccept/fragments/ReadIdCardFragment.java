package com.neusoft.woaccept.fragments;

import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neusoft.woaccept.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by LeoLu on 2016/9/30.
 */
@EFragment(R.layout.fragment_read_id_card)
public class ReadIdCardFragment extends BaseFragment {
    @ViewById
    Button btn_read_card, next_step_button;
    @ViewById
    TextView customer_name, tv_develop_person, certificate_number, certificate_validity, certificate_address, groupinformation_detail, group_id, tv_develop_channel;
    @ViewById
    EditText phone_number, communication_address, contacter, input_phonenumber;
    @ViewById
    RelativeLayout query_information_layout, development_channel_layout, development_person_layout;
    @ViewById
    LinearLayout ll_read_id_card, readidcard_layout;

    @AfterViews
    void afterView() {


    }

    @Click
    void btn_read_card() {
        if ((PackageManager.PERMISSION_GRANTED ==
                getActivity().getPackageManager()
                        .checkPermission("android.permission.BLUETOOTH", getActivity().getPackageName()))) {
                

        }
    }

}

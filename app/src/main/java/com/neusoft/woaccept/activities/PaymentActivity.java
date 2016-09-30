package com.neusoft.woaccept.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.neusoft.woaccept.R;
import com.neusoft.woaccept.customview.MyTitleBar;
import com.neusoft.woaccept.customview.PaymentSegment;
import com.neusoft.woaccept.model.CustomerInfo;
import com.neusoft.woaccept.model.Msg;
import com.neusoft.woaccept.model.ReqBaseModel;
import com.neusoft.woaccept.model.ReqPayment;
import com.neusoft.woaccept.model.ResBaseModel;
import com.neusoft.woaccept.model.ResLogin;
import com.neusoft.woaccept.model.ResPayment;
import com.neusoft.woaccept.rest.MyErrorHandler;
import com.neusoft.woaccept.rest.MyRestClient;
import com.neusoft.woaccept.tools.AndroidTool;
import com.neusoft.woaccept.tools.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.rest.spring.annotations.RestService;


/**
 * Created by LeoLu on 2016/9/12.
 */

@EActivity(R.layout.activity_payment)
public class PaymentActivity extends BaseActivity implements PaymentSegment.OnItemClickListener {

    @ViewById
    MyTitleBar my_title_bar;

    @ViewById
    EditText et_phone_number;

    @ViewById
    TextView txt_money, txt_money_label, txt_submit;

    @ViewById
    TextView custName, certType, certCode, txt_balance, txt_fee, txt_bill, txt_reputation;

    @ViewById
    CheckBox checkBox;

    @ViewById
    PaymentSegment payment_segment;

    @ViewById
    LinearLayout ll_root;

    @StringRes
    String payment_symbol_yuan;

    @Extra
    String phoneNumber;

    @RestService
    MyRestClient myRestClient;

    @Bean
    MyErrorHandler myErrorHandler;

    // isNum:是否输入有效数字  isPhoneNum是否输入电话
    @Extra
    boolean isNum, isPhoneNum, isSearch;

    @Extra
    ResPayment mResponseBaseModel;

    @Extra
    CustomerInfo mCustomerInfo;

    AlertDialog.Builder alertDialog, adb;

    AlertDialog ad;

    String selectValue;

    @AfterInject
    void afterInject() {
        myRestClient.setRestErrorHandler(myErrorHandler);
    }

    @AfterViews
    void afterView() {
        txt_submit.setAlpha(0.5f);
        SpannableString spannableString = new SpannableString(getString(R.string.payment_zero));
        spannableString.setSpan(new RelativeSizeSpan(1.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_money.setText(spannableString);
        payment_segment.setOnItemClickListener(this);
        my_title_bar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentSearchActivity_.intent(PaymentActivity.this).start();
            }
        });
        if (isSearch) {
            my_title_bar.hideRightButton();
            et_phone_number.setVisibility(View.GONE);
            ll_root.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.GONE);
            notifyUI(mResponseBaseModel);
        } else {
            ll_root.setVisibility(View.GONE);
            et_phone_number.setVisibility(View.VISIBLE);
        }
    }

    @AfterTextChange
    void et_phone_number(Editable s) {
        isPhoneNum = s.length() >= 11;
        onItemClickListener(isNum, selectValue);
    }


    private void notifyUI(ResPayment responseBaseModel) {
        txt_balance.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getRealTimeBalance())));
        txt_fee.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getRealTimeFee())));
        txt_bill.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getFee())));
        txt_reputation.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getCreditFee())));
        custName.setText(mCustomerInfo.getCustName());
        certType.setText(mCustomerInfo.getCertType());
        certCode.setText(mCustomerInfo.getCertCode());
    }

    @Click
    void txt_submit() {
        if ((isPhoneNum || isSearch) && isNum) {

            if (adb == null) {
                adb = new AlertDialog.Builder(this);
                adb.setTitle(R.string.dialog_title).setMessage(R.string.dialog_message)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                submit();
                            }
                        });
                ad = adb.create();
                ad.setCanceledOnTouchOutside(false);
            }
            adb.show();
        }
    }

    private void submit() {
        txt_submit.setEnabled(false);
        txt_submit.setText(R.string.payment_ing);
        pay(isSearch ? "1" : "0");
    }

    private void pay(String flag) {
        ReqPayment reqPayment = new ReqPayment();
        ResLogin loginData = app.getResLogin();
        reqPayment.setProvince(loginData.getProvince());
        reqPayment.setCity(loginData.getCity());
        reqPayment.setDistrict(loginData.getDistrict());
        reqPayment.setOperatorId(loginData.getOperatorId_cb());
        reqPayment.setChannelId(loginData.getChannelId());
        reqPayment.setChannelType(loginData.getChannelType());
        reqPayment.setSerialNumber(isSearch ? phoneNumber : et_phone_number.getText().toString().trim());
        reqPayment.setChargeParty("800");
        reqPayment.setServiceClassCode("0000");
        reqPayment.setTradeTypeCode("9999");
        reqPayment.setFlag(flag);
        int money = (int) ((Double.valueOf(txt_money.getText().toString()) * 100));
        reqPayment.setOrderId(AndroidTool.getRandomOrdersId(loginData.getCity()));
        reqPayment.setFee(String.valueOf(money));
        if ("1".equals(flag) && !"4".equals(mResponseBaseModel.getIf34g())) {
            reqPayment.setServiceKind(mCustomerInfo.getServiceKind());
            reqPayment.setCustomerId(mCustomerInfo.getCustomerId());
            reqPayment.setAccountId(mCustomerInfo.getAccountId());
            reqPayment.setUserId(mCustomerInfo.getUserId());
        }
        ReqBaseModel<Msg<ReqPayment>> reqBM = new ReqBaseModel<>();
        Msg<ReqPayment> bmj = new Msg<>();
        bmj.setMsg(reqPayment);
        reqBM.setAction(Constants.PAY_FEE_NO_SEARCH);
        reqBM.setReq(bmj);
        reqBM.setSessionID(loginData.getSessionID());
        payFee(Constants.PAY_FEE_NO_SEARCH, reqBM);
    }

    @Background
    void payFee(String action, ReqBaseModel<Msg<ReqPayment>> reqBaseModel) {
        afterPayFee(myRestClient.payfeenosearch(reqBaseModel));
    }

    @UiThread
    void afterPayFee(ResBaseModel result) {
        Log.e(this.getPackageName() + this.getLocalClassName(), new Gson().toJson(result));
        if (result == null) {
            AndroidTool.showToast(this, no_net);
        } else if ("0000".equals(result.getCode())) {
            AndroidTool.showToast(PaymentActivity.this, result.getDetail());
            if (isSearch) {
                finish();
            }
        } else {
            AndroidTool.showToast(PaymentActivity.this, result.getDetail());
        }
    }

    /**
     * 改变 底部button的状态
     */
    @Override
    public void onItemClickListener(boolean isNum, String selectValue) {
        this.isNum = isNum;
        this.selectValue = selectValue;
        if ((isPhoneNum || isSearch) && isNum) {
            txt_submit.setAlpha(1.0f);
            String temp = AndroidTool.toDouble(Double.valueOf(selectValue));
            int end = temp.indexOf('.');
            SpannableString spannableString = new SpannableString(temp);
            spannableString.setSpan(new RelativeSizeSpan(1.4f), 0, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_money.setText(spannableString);
        } else {
            txt_submit.setAlpha(0.5f);
            SpannableString spannableString = new SpannableString(getString(R.string.payment_zero));
            spannableString.setSpan(new RelativeSizeSpan(1.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_money.setText(spannableString);
        }
        txt_money.setSelected((isPhoneNum || isSearch) && isNum);
        txt_money_label.setSelected((isPhoneNum || isSearch) && isNum);
    }
}

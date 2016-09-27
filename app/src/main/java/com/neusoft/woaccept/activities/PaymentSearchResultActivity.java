package com.neusoft.woaccept.activities;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.neusoft.woaccept.R;
import com.neusoft.woaccept.adapters.CustomerInfoAdapter;
import com.neusoft.woaccept.model.CustomerInfo;
import com.neusoft.woaccept.model.Msg;
import com.neusoft.woaccept.model.ReqBaseModel;
import com.neusoft.woaccept.model.ReqPayment;
import com.neusoft.woaccept.model.ResLogin;
import com.neusoft.woaccept.model.ResPayment;
import com.neusoft.woaccept.tools.AndroidTool;
import com.neusoft.woaccept.tools.Constants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;


/**
 * Created by LeoLu on 2016/9/14.
 */
@EActivity(R.layout.activity_payment_search_result)
public class PaymentSearchResultActivity extends BaseRecyclerViewActivity<CustomerInfo> {

    @ViewById
    TextView btn_payment;

    @ViewById
    TextView txt_balance, txt_fee, txt_bill, txt_reputation;

    @Extra
    String phoneNumber;

    @StringRes
    String payment_symbol_yuan;

    ResPayment<CustomerInfo> responseBaseModel;

    CustomerInfo mCustomerInfo;

    @Bean
    void setAdapter(CustomerInfoAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    public void changeStatue(CustomerInfo obj) {
        mCustomerInfo = obj;
        btn_payment.setSelected(true);
    }

    @AfterViews
    void afterView() {
        ReqBaseModel<Msg<ReqPayment>> reqBM = new ReqBaseModel<>();
        Msg<ReqPayment> bmj = new Msg<>();
        ReqPayment requestBaseModel = new ReqPayment();
        ResLogin loginData = app.getResLogin();
        requestBaseModel.setChannelId(loginData.getChannelId());
        requestBaseModel.setChannelType(loginData.getChannelType());
        requestBaseModel.setOperatorId(loginData.getOperatorId_cb());
        requestBaseModel.setProvince(loginData.getProvince());
        requestBaseModel.setCity(loginData.getCity());
        requestBaseModel.setDistrict(loginData.getDistrict());
        requestBaseModel.setSerialNumber(phoneNumber);
        requestBaseModel.setInfoList("CUST");
        requestBaseModel.setServiceClassCode("0000");
        requestBaseModel.setTradeTypeCode("9999");
        bmj.setMsg(requestBaseModel);
        reqBM.setAction(Constants.QRYPAY_FEE);
        reqBM.setReq(bmj);
        reqBM.setSessionID(loginData.getSessionID());
        qrypayfee(reqBM);
    }

    @Background
    void qrypayfee(ReqBaseModel<Msg<ReqPayment>> model) {
        afterQrypayfee(myRestClient.qrypayfee(model));
    }

    @UiThread
    void afterQrypayfee(ResPayment<CustomerInfo> result) {
        Log.e(this.getPackageName() + this.getLocalClassName(), new Gson().toJson(result));
        if (result == null) {
            AndroidTool.showToast(this, no_net);
            finish();
        } else if ("0000".equals(result.getCode()) || result.getCode() == null) {
            responseBaseModel = result.clone();
            responseBaseModel.setCustInfo(null);
            myAdapter.insertData(result.getCustInfo(), 0);
            notifyUI(result);
        } else {
            AndroidTool.showToast(this, result.getDetail());
            finish();
        }
    }

    private void notifyUI(ResPayment<CustomerInfo> responseBaseModel) {
        txt_balance.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getRealTimeBalance())));
        txt_fee.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getRealTimeFee())));
        txt_bill.setText(String.format(payment_symbol_yuan, AndroidTool.convertDouble(responseBaseModel.getFee())));
        txt_reputation.setText(TextUtils.isEmpty(responseBaseModel.getCreditFee()) ? "0" : responseBaseModel.getCreditFee());
    }

    @Click
    void btn_payment() {
        if (btn_payment.isSelected()) {
            PaymentActivity_.intent(this).phoneNumber(phoneNumber)
                    .isSearch(true).mCustomerInfo(mCustomerInfo)
                    .mResponseBaseModel(responseBaseModel).start();
        }
    }
}

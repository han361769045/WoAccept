package com.neusoft.woaccept.rest;

import com.neusoft.woaccept.model.CustomerInfo;
import com.neusoft.woaccept.model.Msg;
import com.neusoft.woaccept.model.ReqBaseModel;
import com.neusoft.woaccept.model.ReqLogin;
import com.neusoft.woaccept.model.ReqPayment;
import com.neusoft.woaccept.model.ReqSendCode;
import com.neusoft.woaccept.model.ResBaseModel;
import com.neusoft.woaccept.model.ResLogin;
import com.neusoft.woaccept.model.ResLoginSms;
import com.neusoft.woaccept.model.ResPayment;
import com.neusoft.woaccept.tools.Constants;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by Leo on 2016/3/2.
 * http://wcapia.zczczy.com/
 * http://218.61.203.50:8018/
 */
@Rest(rootUrl = "http://130.84.1.100:8090/crm4/rest/", requestFactory = MyOkHttpClientHttpRequestFactory.class, interceptors = {MyInterceptor.class},
        converters = {StringHttpMessageConverter.class, GsonHttpMessageConverter.class, FormHttpMessageConverter.class, ByteArrayHttpMessageConverter.class},
        responseErrorHandler = MyResponseErrorHandlerBean.class)
public interface MyRestClient extends RestClientRootUrl, RestClientSupport, RestClientHeaders, RestClientErrorHandling {

    @Post(Constants.IDENTIFY_ACTION)
    ResLoginSms sendCode(@Body ReqSendCode model);

    @Post(Constants.LOGIN_ACTION)
    ResLogin login(@Body ReqLogin model);

    @Post(Constants.PAY_FEE_NO_SEARCH)
    ResBaseModel payfeenosearch(@Body ReqBaseModel<Msg<ReqPayment>> model);

    @Post(Constants.PAYFEENEW)
    ResBaseModel payfeenew(@Body ReqBaseModel<Msg<ReqPayment>> model);

    @Post(Constants.PAY_FEE)
    ResBaseModel payfee(@Body ReqBaseModel<Msg<ReqPayment>> model);

    @Post(Constants.QRYPAY_FEE)
    ResPayment<CustomerInfo> qrypayfee(@Body ReqBaseModel<Msg<ReqPayment>> model);
}


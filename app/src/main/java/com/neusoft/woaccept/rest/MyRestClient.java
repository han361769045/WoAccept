package com.neusoft.woaccept.rest;

import com.neusoft.woaccept.BuildConfig;
import com.neusoft.woaccept.model.CustomerInfo;
import com.neusoft.woaccept.model.Msg;
import com.neusoft.woaccept.model.PhoneNumber;
import com.neusoft.woaccept.model.ReqBaseModel;
import com.neusoft.woaccept.model.ReqLogin;
import com.neusoft.woaccept.model.ReqPayment;
import com.neusoft.woaccept.model.ReqSelectPhoneNumber;
import com.neusoft.woaccept.model.ReqSendCode;
import com.neusoft.woaccept.model.ResBaseModel;
import com.neusoft.woaccept.model.ResLogin;
import com.neusoft.woaccept.model.ResLoginSms;
import com.neusoft.woaccept.model.ResPayment;
import com.neusoft.woaccept.tools.Constants;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Field;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Part;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.util.List;

/**
 * Created by Leo on 2016/3/2.
 * http://wcapia.zczczy.com/
 * http://218.61.203.50:8018/
 * http://130.84.1.100:8090/crm4/rest/
 */
@Rest(rootUrl = BuildConfig.ROOT_URL, requestFactory = MyOkHttpClientHttpRequestFactory.class, interceptors = {MyInterceptor.class},
        converters = {StringHttpMessageConverter.class, MyGsonHttpMessageConverter.class, FormHttpMessageConverter.class, ByteArrayHttpMessageConverter.class, ResourceHttpMessageConverter.class},
        responseErrorHandler = MyResponseErrorHandlerBean.class)
public interface MyRestClient extends RestClientRootUrl, RestClientSupport, RestClientHeaders, RestClientErrorHandling {

    @Post(Constants.IDENTIFY_ACTION)
    @Header(name = "isLoading", value = "true")
    ResLoginSms sendCode(@Body ReqSendCode model);

    @Post(Constants.LOGIN_ACTION)
    ResLogin login(@Body ReqLogin model);

    @Post(Constants.PAY_FEE_NO_SEARCH)
    ResBaseModel payfeenosearch(@Body ReqBaseModel<Msg<ReqPayment>> model);

    @Post(Constants.QRYPAY_FEE)
    ResPayment<CustomerInfo> qrypayfee(@Body ReqBaseModel<Msg<ReqPayment>> model);

    @Post(Constants.SERVICE_IDQRY_ACTION)
    ResBaseModel<List<PhoneNumber>> serviceidqry(@Body ReqBaseModel<Msg<ReqSelectPhoneNumber>> model);

    @Post("/events/{id}")
    String addEvent(@Path String id, @Field String eventName);

    @Post("/events/{id}")
    String addEvent(@Path String id, @Part FileSystemResource image);

    @Get("http://gdown.baidu.com/data/wisegame/55dc62995fe9ba82/jinritoutiao_448.apk")
    @Accept(MediaType.APPLICATION_OCTET_STREAM)
    ByteArrayResource downlaod();

}


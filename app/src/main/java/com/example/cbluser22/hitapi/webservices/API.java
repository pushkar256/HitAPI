package com.example.cbluser22.hitapi.webservices;

import com.example.cbluser22.hitapi.pojo.PojoBase;
import com.example.cbluser22.hitapi.utils.Constants;
import com.example.cbluser22.hitapi.webservices.models.SignupModel;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by cbluser22 on 8/2/17.
 */

public interface API {


  /*  @POST(Constants.SIGNUP_URL)
    Call<PojoBase> signup(@Body SignupModel model);*/

    @FormUrlEncoded
    @POST(Constants.SIGNUP_URL)
    Call <PojoBase> signup(@FieldMap Map<String,String> map);
}

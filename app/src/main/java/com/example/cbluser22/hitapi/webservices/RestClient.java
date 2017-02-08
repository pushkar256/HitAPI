package com.example.cbluser22.hitapi.webservices;

import com.example.cbluser22.hitapi.utils.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by cbluser22 on 8/2/17.
 */

public class RestClient {
    private static API REST_API;

    public static API getClient()
    {
        if(REST_API==null)
        {
            createClient();
        }
        return REST_API;
    }

    private static void createClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(1, TimeUnit.MINUTES);

        Retrofit.Builder mBuilder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create());
        REST_API=mBuilder.build().create(API.class);
    }
}

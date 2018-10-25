package com.ph.idisciple.idiscipleapp.data.remote;

import com.ph.idisciple.idiscipleapp.BuildConfig;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private UserService mSetupService;
    private static RestClient mRestClient;

    public RestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.ApiKey)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build());

        Retrofit retrofit = builder.build();
        mSetupService = retrofit.create(UserService.class);

    }

    public static synchronized RestClient getInstance() {
        if (mRestClient == null) {
            mRestClient = new RestClient();
        }

        return mRestClient;
    }

    public UserService getSetupService() {
        return mSetupService;
    }
}

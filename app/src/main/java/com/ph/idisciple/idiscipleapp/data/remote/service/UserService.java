package com.ph.idisciple.idiscipleapp.data.remote.service;

import com.ph.idisciple.idiscipleapp.data.remote.model.base.Wrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.LoginResponse;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.UserAccess;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface UserService {

    @POST("auth/login")
    Call<Wrapper<LoginResponse>> loginUser(@QueryMap Map<String, String> body);

    @POST("user/first-time/")
    Call<Wrapper<UserAccess>> firstPasswordUpdate(@QueryMap Map<String, String> body);

}

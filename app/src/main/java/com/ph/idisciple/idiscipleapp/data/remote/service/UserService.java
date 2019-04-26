package com.ph.idisciple.idiscipleapp.data.remote.service;

import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.ListWrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.Wrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.LoginResponse;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.UserAccount;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UserService {

    @POST("auth/login")
    Call<ListWrapper<LoginResponse>> loginUser(@QueryMap Map<String, String> body);

    @PUT("user/first-time/{userId}")
    Call<Wrapper<UserAccount>> firstPasswordUpdate(@Path("userId") String userId, @Query("new_password") String newPassword);

    @GET("user/logout")
    Call<BaseApi> logoutUser(@Query("user_id") String userId);

    @POST("user/reset-password")
    Call<BaseApi> resetPassword(@Query("email") String email);

    @POST("user/photo")
    Call<Wrapper<Profile>> uploadPhoto(@QueryMap Map<String, String> body);

    @Multipart
    @POST("user/photo")
    Call<Wrapper<Profile>> uploadPhoto(@Part("data") RequestBody data, @Part MultipartBody.Part imagePic);


}

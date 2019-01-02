package com.ph.idisciple.idiscipleapp.data.remote.service;

import com.ph.idisciple.idiscipleapp.data.remote.model.base.Wrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.ContentResponseWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContentService {

    @GET("content/all?")
    Call<Wrapper<ContentResponseWrapper>> getContent(@Query("user_id") String userId);
}
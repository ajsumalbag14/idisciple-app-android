package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentGetDataWrapper<T> {

    @SerializedName("data")
    private  List<T> data;

    public List<T> getData() {
        return data;
    }
}

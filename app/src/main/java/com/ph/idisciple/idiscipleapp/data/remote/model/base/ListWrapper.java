package com.ph.idisciple.idiscipleapp.data.remote.model.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jeven Soquita on 15/01/2018.
 */

public class ListWrapper<T> extends BaseApi {

    @SerializedName("data")
    private List<T> data;

    public List<T> getData() {
        return data;
    }
}

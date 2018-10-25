package com.ph.idisciple.idiscipleapp.data.remote.model.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jeven Soquita on 15/01/2018.
 */

public class Wrapper<T> extends BaseApi {

    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }
}

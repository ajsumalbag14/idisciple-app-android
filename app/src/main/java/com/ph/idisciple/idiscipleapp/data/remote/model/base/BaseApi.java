package com.ph.idisciple.idiscipleapp.data.remote.model.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jeven Soquita on 15/01/2018.
 */

public class BaseApi {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}

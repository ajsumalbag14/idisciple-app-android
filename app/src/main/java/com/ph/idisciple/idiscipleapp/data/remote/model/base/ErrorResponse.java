package com.ph.idisciple.idiscipleapp.data.remote.model.base;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

}

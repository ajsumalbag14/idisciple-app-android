package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class LoginErrorResponse {

    @SerializedName("text")
    private JsonObject data;

    public JsonObject getError() {
        return data;
    }

}

package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;

public class ContentDetails {

    @SerializedName("update_now")
    private String updateDetails;
    @SerializedName("path_file")
    private String jsonPathFile;

    public String getUpdateDetails(){
        return updateDetails;
    }

    public String getJsonPathFile(){
        return jsonPathFile;
    }
}

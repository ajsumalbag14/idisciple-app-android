package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;

public class ContentResponseWrapper {

    @SerializedName("assets")
    private ContentResponse assetsData;

    public ContentResponse getAssetsData() {
        return assetsData;
    }
}

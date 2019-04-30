package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Resource extends RealmObject {

    @PrimaryKey
    @SerializedName("resource_id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getResourceTitle() {
        return title;
    }
    public void setResourceTitle(String title) {
        this.title = title;
    }

    public String getResourceType() {
        return type;
    }
    public void setResourceType(String type) {
        this.type = type;
    }

    public String getResourceUrl() {
        return url;
    }
    public void setResourceUrl(String url) {
        this.url = url;
    }

}

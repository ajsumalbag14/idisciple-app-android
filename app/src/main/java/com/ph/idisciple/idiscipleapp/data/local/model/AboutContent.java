package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AboutContent extends RealmObject {

    @PrimaryKey
    @SerializedName("about_id")
    private int id;
    @SerializedName("title")
    private String aboutTitle;
    @SerializedName("content")
    private String aboutContent;

    public int getAboutOrder(){
        return id;
    }
    public void setAboutOrder(int order){
        this.id = order;
    }

    public String getAboutTitle(){
        return aboutTitle == null ? "" : aboutTitle;
    }
    public void setAboutTitle(String text){
        this.aboutTitle = aboutTitle;
    }

    public String getAboutContent(){
        return aboutContent == null ? "" : aboutContent;
    }
    public void setAboutContent(String text){
        this.aboutContent = aboutContent;
    }
}

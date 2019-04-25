package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.abouttab;

import com.google.gson.annotations.SerializedName;

public class AboutContent {

    @SerializedName("about_id")
    private int aboutOrder;
    @SerializedName("title")
    private String aboutTitle;
    @SerializedName("content")
    private String aboutContent;

    public int getAboutOrder(){
        return aboutOrder;
    }
    public void setAboutOrder(int order){
        this.aboutOrder = order;
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

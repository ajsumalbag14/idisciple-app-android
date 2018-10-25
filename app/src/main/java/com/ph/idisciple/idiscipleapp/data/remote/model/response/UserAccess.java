package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;

public class UserAccess {

    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("api_token")
    private String apiToken;
    @SerializedName("fcm_token")
    private String fcmToken;


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getApiToken() {
        return apiToken;
    }
    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }


    public String getFcmToken() {
        return fcmToken;
    }
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

}

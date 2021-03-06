package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;

public class UserAccount extends BaseApi {

    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;
    @SerializedName("api_token")
    private String token;
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


    public String getToken() {
        return token;
    }
    public void setToken(String apiToken) {
        this.token = apiToken;
    }


    public String getFcmToken() {
        return fcmToken;
    }
    public void setFcmToken(boolean isFirstTimeUser) {
        this.fcmToken = isFirstTimeUser ? "Yes" : "No";
    }

}

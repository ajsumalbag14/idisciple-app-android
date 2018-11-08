package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;

public class LoginUserAccount extends BaseApi {

    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;
    @SerializedName("token")
    private String token;
    @SerializedName("first_time_user")
    private String firstTimeUserTag;


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


    public String getFirstTimeUserTag() {
        return firstTimeUserTag;
    }
    public boolean isFirstTimeUser() {
        return firstTimeUserTag.equals("Yes");
    }
    public void setFirstTimeUserTag(boolean isFirstTimeUser) {
        this.firstTimeUserTag = isFirstTimeUser ? "Yes" : "No";
    }

}

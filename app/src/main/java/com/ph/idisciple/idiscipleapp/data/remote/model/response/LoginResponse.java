package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.ph.idisciple.idiscipleapp.data.remote.model.Profile;

public class LoginResponse {

    @SerializedName("user_account")
    private UserAccount classUserAccount;
    @SerializedName("profile")
    private Profile classProfile;

    public UserAccount getUserAccess() {
        return classUserAccount;
    }
    public void setUserAccess(UserAccount classUserAccess) {
        this.classUserAccount = classUserAccess;
    }

    public Profile getProfile() {
        return classProfile;
    }
    public void setUserAccess(Profile classProfile) {
        this.classProfile = classProfile;
    }

}

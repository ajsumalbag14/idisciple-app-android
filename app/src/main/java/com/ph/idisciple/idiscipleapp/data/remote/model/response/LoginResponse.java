package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.ph.idisciple.idiscipleapp.data.remote.model.Profile;

public class LoginResponse {

    @SerializedName("user_access")
    private UserAccess classUserAccess;
    @SerializedName("profile")
    private Profile classProfile;

    public UserAccess getUserAccess() {
        return classUserAccess;
    }
    public void setUserAccess(UserAccess classUserAccess) {
        this.classUserAccess = classUserAccess;
    }

    public Profile getProfile() {
        return classProfile;
    }
    public void setUserAccess(Profile classProfile) {
        this.classProfile = classProfile;
    }

}

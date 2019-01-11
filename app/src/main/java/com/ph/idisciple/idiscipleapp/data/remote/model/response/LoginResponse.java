package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.remote.model.EventDetails;

public class LoginResponse {

    @SerializedName("user_account")
    private LoginUserAccount classUserAccount;
    @SerializedName("profile")
    private Profile classProfile;
    @SerializedName("event_details")
    private EventDetails eventDetails;

    public LoginUserAccount getUserAccess() {
        return classUserAccount;
    }
    public void setUserAccess(LoginUserAccount classUserAccess) {
        this.classUserAccount = classUserAccess;
    }

    public Profile getProfile() {
        return classProfile;
    }
    public void setUserAccess(Profile classProfile) {
        this.classProfile = classProfile;
    }

    public EventDetails getEventDetails(){
        return this.eventDetails;
    }
    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
}

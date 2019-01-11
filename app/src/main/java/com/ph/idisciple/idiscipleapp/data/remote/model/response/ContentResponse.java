package com.ph.idisciple.idiscipleapp.data.remote.model.response;

import com.google.gson.annotations.SerializedName;

public class ContentResponse {

    @SerializedName("speakers")
    private ContentDetails contentSpeakers;
    @SerializedName("workshops")
    private ContentDetails contentWorkshops;
    @SerializedName("schedule")
    private ContentDetails contentSchedule;
    @SerializedName("profile")
    private ContentDetails contentProfile;
    @SerializedName("family_groups")
    private ContentDetails contentFamilyGroup;

    public ContentDetails getContentSpeakers() {
        return contentSpeakers;
    }
    public void setContentSpeakers(ContentDetails contentSpeakers) {
        this.contentSpeakers = contentSpeakers;
    }

    public ContentDetails getContentWorkshops() {
        return contentWorkshops;
    }
    public void setContentWorkshops(ContentDetails contentWorkshops) {
        this.contentWorkshops = contentWorkshops;
    }

    public ContentDetails getContentSchedule() {
        return contentSchedule;
    }
    public void setContentSchedule(ContentDetails contentSchedule) {
        this.contentSchedule = contentSchedule;
    }

    public ContentDetails getContentProfile() {
        return contentProfile;
    }
    public void setContentProfile(ContentDetails contentProfile) {
        this.contentProfile = contentProfile;
    }

    public ContentDetails getContentFamilyGroup() {
        return contentFamilyGroup;
    }
    public void setContentFamilyGroup(ContentDetails contentFamilyGroup) {
        this.contentFamilyGroup = contentFamilyGroup;
    }

}

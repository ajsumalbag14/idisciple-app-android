package com.ph.idisciple.idiscipleapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class Speaker {

    @SerializedName("id")
    private String speakerId;
    @SerializedName("name")
    private String speakerName;

    @SerializedName("bio")
    private String speakerBio;
    @SerializedName("nationality")
    private String speakerNationality;

    @SerializedName("facebook")
    private String speakerFacebook;
    @SerializedName("twitter")
    private String speakerTwitter;
    @SerializedName("website")
    private String speakerWebsite;

    @SerializedName("img_path")
    private String speakerImagePath;
    @SerializedName("img_name")
    private String speakerImageName;

    @SerializedName("plenary_title")
    private String speakerPlanaryTitle;
    @SerializedName("plenary_schedule_date")
    private String speakerPlanaryScheduleDate;
    @SerializedName("plenary_schedule_time")
    private String speakerPlanaryScheduleTime;

    @SerializedName("workshop_id")
    private String speakerWorkshopId;
    @SerializedName("workshop_title")
    private String speakerWorkshopTitle;
    @SerializedName("workshop_schedule_date")
    private String speakerWorkshopScheduleDate;
    @SerializedName("workshop_schedule_time")
    private String speakerWorkshopScheduleTime;

    public Speaker(String name, String topic, String date){
        speakerName = name;
        speakerPlanaryTitle = topic;
        speakerPlanaryScheduleDate = date;
    }

    public String getSpeakerId(){
        return speakerId;
    }
    public void setSpeakerId(String id){
        this.speakerId = id;
    }

    public String getSpeakerName(){
        return speakerName;
    }
    public void setSpeakerName(String speakerName){
        this.speakerName = speakerName;
    }

    public String getSpeakerBio(){
        return speakerBio;
    }
    public void setSpeakerBio(String speakerBio){
        this.speakerBio = speakerBio;
    }

    public String getSpeakerNationality(){
        return speakerNationality;
    }
    public void setSpeakerNationality(String speakerNationality){
        this.speakerNationality = speakerNationality;
    }


    public String getSpeakerFacebookAccount(){
        return speakerFacebook;
    }
    public void setSpeakerFacebookAccount(String speakerFacebook){
        this.speakerFacebook = speakerFacebook;
    }

    public String getSpeakerTwitterAccount(){
        return speakerTwitter;
    }
    public void setSpeakerTwitterAccount(String speakerTwitter){
        this.speakerTwitter = speakerTwitter;
    }

    public String getSpeakerWebsite(){
        return speakerWebsite;
    }
    public void setSpeakerWebsite(String speakerWebsite){
        this.speakerWebsite = speakerWebsite;
    }


    public String getSpeakerImageUrl(){
        return speakerImagePath + speakerImageName;
    }
    public void setSpeakerImagePath(String speakerImagePath){
        this.speakerImagePath = speakerImagePath;
    }
    public void setSpeakerImageName(String speakerImageName){
        this.speakerImageName = speakerImageName;
    }


    public String getSpeakerPlanaryTitle(){
        return speakerPlanaryTitle;
    }
    public void setSpeakerPlanaryTitle(String speakerPlanaryTitle){
        this.speakerPlanaryTitle = speakerPlanaryTitle;
    }

    public String getSpeakerPlanaryScheduleDate(){
        return speakerPlanaryScheduleDate;
    }
    public void setSpeakerPlanaryScheduleDate(String speakerPlanaryScheduleDate){
        this.speakerPlanaryScheduleDate = speakerPlanaryScheduleDate;
    }

    public String getSpeakerPlanaryScheduleTime(){
        return speakerPlanaryScheduleTime;
    }
    public void setSpeakerPlanaryScheduleTime(String speakerPlanaryScheduleTime){
        this.speakerPlanaryScheduleTime = speakerPlanaryScheduleTime;
    }

    public String getSpeakerWorkshopId(){
        return speakerWorkshopId;
    }
    public void setSpeakerWorkshopId(String speakerWorkshopId){
        this.speakerWorkshopId = speakerWorkshopId;
    }

    public String getSpeakerWorkshopTitle(){
        return speakerWorkshopTitle;
    }
    public void setSpeakerWorkshopTitle(String speakerWorkshopTitle){
        this.speakerWorkshopTitle = speakerWorkshopTitle;
    }

    public String getSpeakerWorkshopScheduleDate(){
        return speakerWorkshopScheduleDate;
    }
    public void setSpeakerWorkshopScheduleDate(String speakerWorkshopScheduleDate){
        this.speakerWorkshopScheduleDate = speakerWorkshopScheduleDate;
    }

    public String getSpeakerWorkshopScheduleTime(){
        return speakerWorkshopScheduleTime;
    }
    public void setSpeakerWorkshopScheduleTime(String speakerWorkshopScheduleTime){
        this.speakerWorkshopScheduleTime = speakerWorkshopScheduleTime;
    }

}

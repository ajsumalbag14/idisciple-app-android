package com.ph.idisciple.idiscipleapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("sched_id")
    private String scheduleId;
    @SerializedName("sched_name")
    private String scheduleName;
    @SerializedName("sched_venue")
    private String scheduleVenue;
    @SerializedName("sched_date")
    private String scheduleDate;

    @SerializedName("sched_start_time")
    private String scheduleStartTime;
    @SerializedName("sched_end_time")
    private String scheduleEndTime;

    public String getScheduleId(){
        return scheduleId;
    }
    public void setScheduleId(String id){
        this.scheduleId = id;
    }

    public String getScheduleName(){
        return scheduleName;
    }
    public void setScheduleName(String scheduleName){
        this.scheduleName = scheduleName;
    }

    public String getScheduleVenue(){
        return scheduleVenue;
    }
    public void setScheduleVenue(String scheduleVenue){
        this.scheduleVenue = scheduleVenue;
    }


    public String getScheduleDate(){
        return scheduleDate;
    }
    public void setScheduleDate(String scheduleVenue){
        this.scheduleVenue = scheduleVenue;
    }


    public String getScheduleStartTime(){
        return scheduleStartTime;
    }
    public void setScheduleStartTime(String scheduleStartTime){
        this.scheduleStartTime = scheduleStartTime;
    }
    public String getScheduleEndTime(){
        return scheduleEndTime;
    }
    public void setScheduleEndTime(String scheduleEndTime){
        this.scheduleEndTime = scheduleEndTime;
    }
}

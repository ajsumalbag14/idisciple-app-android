package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Schedule  extends RealmObject {

    @PrimaryKey
    @SerializedName("sched_id")
    private String id;
    @SerializedName("sched_name")
    private String scheduleName;
    @SerializedName("sched_venue")
    private String scheduleVenue;
    @SerializedName("sched_date")
    private String scheduleDate;
    @SerializedName("workshop_id")
    private String workshopId;

    @SerializedName("sched_start_time")
    private String scheduleStartTime;
    @SerializedName("sched_end_time")
    private String scheduleEndTime;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getWorkshopId(){
        return workshopId;
    }
    public void setWorkshopId(String id){
        this.workshopId = id;
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


    public String getScheduleDateString(){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendarParsedDate = Calendar.getInstance();
            calendarParsedDate.setTime(formatter.parse(scheduleDate));
            return String.format("%1$02d/%2$s/%3$s", calendarParsedDate.get(Calendar.MONTH) + 1, calendarParsedDate.get(Calendar.DAY_OF_MONTH), calendarParsedDate.get(Calendar.YEAR));
        } catch (ParseException ex){
            return scheduleDate;
        }
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

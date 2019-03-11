package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Workshop extends RealmObject {

    @PrimaryKey
    @SerializedName("workshop_id")
    private String id;
    @SerializedName("workshop_name")
    private String workshopName;

    @SerializedName("description")
    private String workshopDescription;
    @SerializedName("outline")
    private String workshopOutline;
    @SerializedName("facilitator")
    private String workshopFacilitator;
    @SerializedName("location")
    private String workshopLocation;

    @SerializedName("img_path")
    private String workshopImagePath;
    @SerializedName("img_name")
    private String workshopImageName;

    @SerializedName("workshop_schedule_date")
    private String workshopScheduleDate;
    @SerializedName("workshop_schedule_time")
    private String workshopScheduleTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getWorkshopName() {
        return workshopName;
    }
    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getWorkshopDescription() {
        return workshopDescription;
    }
    public void setWorkshopDescription(String workshopDescription) {
        this.workshopDescription = workshopDescription;
    }

    public String getWorkshopOutline() {
        return workshopOutline;
    }
    public void setWorkshopOutline(String workshopOutline) {
        this.workshopOutline = workshopOutline;
    }

    public String getWorkshopFacilitator() {
        return workshopFacilitator;
    }
    public void setWorkshopFacilitator(String workshopFacilitator) {
        this.workshopFacilitator = workshopFacilitator;
    }

    public String getWorkshopLocation() {
        return workshopLocation;
    }
    public void setWorkshopLocation(String workshopLocation) {
        this.workshopLocation = workshopLocation;
    }

    public String getWorkshopScheduleDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendarParsedDate = Calendar.getInstance();
            calendarParsedDate.setTime(formatter.parse(workshopScheduleDate));
            return String.format("%1$02d/%2$s/%3$s", calendarParsedDate.get(Calendar.MONTH) + 1, calendarParsedDate.get(Calendar.DAY_OF_MONTH), calendarParsedDate.get(Calendar.YEAR));
        } catch (ParseException ex){
            return workshopScheduleDate;
        }
    }
    public void setWorkshopScheduleDate(String workshopScheduleDate) {
        this.workshopScheduleDate = workshopScheduleDate;
    }

    public String getWorkshopScheduleTime() {
        return workshopScheduleTime;
    }
    public void setWorkshopScheduleTime(String workshopScheduleTime) {
        this.workshopScheduleTime = workshopScheduleTime;
    }

    public String getWorkshopImageUrl(){
        return workshopImagePath + workshopImageName;
    }
    public void setWorkshopImagePath(String workshopImagePath){
        this.workshopImagePath = workshopImagePath;
    }
    public void setWorkshopImageName(String workshopImageName){
        this.workshopImageName = workshopImageName;
    }
}

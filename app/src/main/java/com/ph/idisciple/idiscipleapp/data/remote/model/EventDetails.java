package com.ph.idisciple.idiscipleapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class EventDetails {

    @SerializedName("workshop_id_1")
    private String workshipId1;
    @SerializedName("workshop_id_2")
    private String workshipId2;
    @SerializedName("family_group_id")
    private String familyGroupId;
    @SerializedName("tshirt_size")
    private String tshirtSize;
    @SerializedName("device")
    private String deviceOs;
    @SerializedName("city_tour")
    private String tagCityTour;
    @SerializedName("room_number")
    private String rooomNumber;

    public String getWorkshopId1(){
        return workshipId1;
    }
    public void setWorkshopId1(String workshopId){
        workshipId1 = workshopId;
    }

    public String getWorkshopId2(){
        return workshipId2;
    }
    public void setWorkshopId2(String workshopId){
        workshipId2 = workshopId;
    }

    public String getFamilyGroupId(){
        return familyGroupId;
    }
    public void setFamilyGroupId(String familyGroupId){
        familyGroupId = familyGroupId;
    }

    public String getTshirtSize(){
        return tshirtSize;
    }
    public void setTshirtSize(String tshirtSize){
        tshirtSize = tshirtSize;
    }

    public String getDeviceOs(){
        return deviceOs;
    }
    public void setDeviceOs(String deviceOs){
        deviceOs = deviceOs;
    }

    public boolean isCityTour(){
        return tagCityTour == "Yes";
    }
    public String getTagCityTour(){
        return tagCityTour;
    }
    public void setTagCityTour(String tagCityTour){
        tagCityTour = tagCityTour;
    }

    public String getRooomNumber(){
        return rooomNumber;
    }
    public void setRooomNumber(String rooomNumber){
        rooomNumber = rooomNumber;
    }
}

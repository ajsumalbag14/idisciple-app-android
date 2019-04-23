package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Profile  extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String userFullName;
    @SerializedName("firstname")
    private String userFirstName;
    @SerializedName("lastname")
    private String userLastName;
    @SerializedName("middlename")
    private String userMiddleName;
    @SerializedName("nickname")
    private String userNickName;
    @SerializedName("gender")
    private String userGender;
    @SerializedName("country")
    private String userCountry;

    @SerializedName("fg_id")
    private String userFamilyGroupId;
    @SerializedName("fg_name")
    private String userFamilyGroupName;

    @SerializedName("workshop_number_1")
    private String userWorkshop1;
    @SerializedName("workshop_number_2")
    private String userWorkshop2;

    @SerializedName("img_path")
    private String userImagePath;
    @SerializedName("img_name")
    private String userImageName;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }
    public String getUserFullNameCapslock() {
        // For ORDERBY query, it seems to use UNIX code in ordering
        return userFullName.toUpperCase();
    }
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }
    public void setUserMiddleName(String password) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserNickName() {
        return userNickName;
    }
    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }


    public String getUserGender() {
        return userGender;
    }
    public void setUserGender(String gender) {
        this.userGender = gender;
    }

    public String getUserCountry() {
        return userCountry;
    }
    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserFamilyGroupId() {
        return userFamilyGroupId;
    }
    public void setUserFamilyGroupId(String userFamilyGroupId) {
        this.userFamilyGroupId = userFamilyGroupId;
    }

    public String getUserFamilyGroupName() {
        return userFamilyGroupName;
    }
    public void setUserFamilyGroupName(String userFamilyGroupName) {
        this.userFamilyGroupName = userFamilyGroupName;
    }

    public String getUserWorkshop1() {
        return userWorkshop1 == null ? "0" : userWorkshop1;
    }
    public void setUserWorkshop1(String userWorkshop1) {
        this.userWorkshop1 = userWorkshop1;
    }

    public String getUserWorkshop2() {
        return userWorkshop2 == null ? "0" : userWorkshop2;
    }
    public void setUserWorkshop2(String userWorkshop2) {
        this.userWorkshop2 = userWorkshop2;
    };


    public String getUserImageUrl(){
        return userImagePath + userImageName;
    }
    public void setUserImagePath(String userImagePath){
        this.userImagePath = userImagePath;
    }
    public void setUserImageName(String userImageName){
        this.userImageName = userImageName;
    }
}

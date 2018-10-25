package com.ph.idisciple.idiscipleapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("middlename")
    private String middleName;
    @SerializedName("nickname")
    private String nickName;
    @SerializedName("mobile_no")
    private String mobileNumber;

    @SerializedName("birthdate")
    private String birthdate;
    @SerializedName("gender")
    private String gender;
    @SerializedName("country")
    private String country;
    @SerializedName("is_pwd")
    private int isPwdTag;
    @SerializedName("created_at")
    private String createdAt;


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String password) {
        this.middleName = middleName;
    }


    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public Boolean isPwd () {
        return isPwdTag == 1;
    }
    public void setIsPwd(boolean isPwd) {
        this.isPwdTag = isPwd ? 1 : 0;
    }



    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}

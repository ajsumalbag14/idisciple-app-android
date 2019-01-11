package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FamilyGroup extends RealmObject {

    @PrimaryKey
    @SerializedName("fg_id")
    private String id;
    @SerializedName("fg_name")
    private String familyGroupName;
    @SerializedName("fg_lead_id")
    private String familyGroupLeadId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFamilyGroupName() {
        return familyGroupName;
    }
    public void setFamilyGroupName(String familyGroupName) {
        this.familyGroupName = familyGroupName;
    }

    public String getFamilyGroupLeadId() {
        return familyGroupLeadId;
    }
    public void setFamilyGroupLeadId(String familyGroupLeadId) {
        this.familyGroupLeadId = familyGroupLeadId;
    }

}

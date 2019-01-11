package com.ph.idisciple.idiscipleapp.data.local.model;

import io.realm.RealmObject;

/**
 * Current Profile details
 */
public class ProfileObject extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_ITEM = "item";
    public static final String FIELD_VALUE = "value";

    private String item;
    private String value;

    public enum ProfileType {
        USER_ID, USERNAME,
        FIRST_NAME, LAST_NAME, MIDDLENAME, NICKNAME,
        GENDER, COUNTRY,
        WORKSHOP_ID1, WORKSHOP_ID2, FAMILY_GROUP_ID,
        TSHIRT_SIZE, IS_CITY_TOUR, ROOM_NUMBER
    }

    public void setItemName(String name) {
        this.item = name;
    }

    public String getItemValue() {
        return value;
    }

    public void setItemValue(String value) {
        this.value = value;
    }

}

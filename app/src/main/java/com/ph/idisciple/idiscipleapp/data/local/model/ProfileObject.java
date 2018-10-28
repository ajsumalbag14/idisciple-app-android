package com.ph.idisciple.idiscipleapp.data.local.model;

import io.realm.RealmObject;

public class ProfileObject extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_ITEM = "item";
    public static final String FIELD_VALUE = "value";

    private String item;
    private String value;

    public enum ProfileType {
        USER_ID, USERNAME,
        FIRST_NAME, LAST_NAME, MIDDLENAME, NICKNAME,
        MOBILE_NUMBER, BIRTHDATE,
        GENDER, COUNTRY, IS_PWD,
        CREATED_AT_DATE, UPDATED_AT_DATE
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

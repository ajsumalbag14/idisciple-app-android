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
        USER_ID
    }

    public String getItemName() {
        return item;
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

package com.ph.idisciple.idiscipleapp.data.local.model;

import io.realm.RealmObject;

/**
 * Created by Charlene Chiang on 16/11/2017.
 * Contains All API Details and Settings
 */
public class KeySettings extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_ITEM = "item";
    public static final String FIELD_VALUE = "value";

    private String item;
    private String value;

    public enum ItemType {
        IS_LOGGED_IN, TOKEN,
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

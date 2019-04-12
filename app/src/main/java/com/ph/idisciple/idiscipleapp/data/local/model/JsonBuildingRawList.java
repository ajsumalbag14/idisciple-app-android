package com.ph.idisciple.idiscipleapp.data.local.model;

import java.util.ArrayList;

public class JsonBuildingRawList {

    private ArrayList<JsonBuildingItemRaw> data;

    public ArrayList<JsonBuildingItemRaw> getList() {
        return data;
    }
    public void setList(ArrayList<JsonBuildingItemRaw> itemList) {
        this.data = itemList;
    }
}

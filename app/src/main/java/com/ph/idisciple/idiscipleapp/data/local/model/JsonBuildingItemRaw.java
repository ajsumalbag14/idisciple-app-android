package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonBuildingItemRaw {

    private int id;
    @SerializedName("item_letter")
    private String itemLetter;

    @SerializedName("item_name")
    private String itemName;

    @SerializedName("item_description")
    private String itemDescription;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNameLowercase() {
        return itemName.toLowerCase();
    }

    public String getItemLetter() {
        return itemLetter;
    }

    public void setItemLetter(String itemLetter) {
        this.itemLetter = itemLetter;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public JsonBuildingItemRaw fromJson(JSONObject jsonObject) {
        JsonBuildingItemRaw item = new JsonBuildingItemRaw();

        // Deserialize json into object fields
        try {
            item.id = jsonObject.getInt("id");
            item.itemLetter = jsonObject.getString("item_letter");
            item.itemName = jsonObject.getString("item_name");
            item.itemDescription = jsonObject.getString("item_description");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return item;
    }
}

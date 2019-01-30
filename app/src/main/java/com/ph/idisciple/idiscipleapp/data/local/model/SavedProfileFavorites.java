package com.ph.idisciple.idiscipleapp.data.local.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SavedProfileFavorites extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;
    private boolean isFavorite = false;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public boolean isTagAsFavorite(){
        return isFavorite;
    }
    public void setAsFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }
}

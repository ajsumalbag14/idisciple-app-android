package com.ph.idisciple.idiscipleapp.data.local.repository.SavedProfileFavorites;

import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.SavedProfileFavorites;

import java.util.List;

public interface ISavedProfileFavoritesRepository {
    SavedProfileFavorites findItemById(String id);

    void addItemList(List<Profile> itemList);

    void setAsFavorite(String id, boolean isFavorite, final onSaveCallback callback);

    int size();

    interface onSaveCallback {
        void onSuccess(boolean isFavoriteSet);
    }
}

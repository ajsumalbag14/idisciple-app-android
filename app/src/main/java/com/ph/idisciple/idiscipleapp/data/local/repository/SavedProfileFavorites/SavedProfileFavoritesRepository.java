package com.ph.idisciple.idiscipleapp.data.local.repository.SavedProfileFavorites;

import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.SavedProfileFavorites;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class SavedProfileFavoritesRepository extends BaseRepository implements ISavedProfileFavoritesRepository {

    @Override
    public ArrayList<SavedProfileFavorites> getContentList() {
        RealmResults<SavedProfileFavorites> realmResults = realm.where(SavedProfileFavorites.class).findAll();
        ArrayList<SavedProfileFavorites> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public SavedProfileFavorites findItemById(String id) {
        return realm.where(SavedProfileFavorites.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<Profile> itemList) {
        for (final Profile profile : itemList) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    SavedProfileFavorites storage = realm.createObject(SavedProfileFavorites.class, profile.getId());
                    storage.setAsFavorite(false);
                    realm.insertOrUpdate(storage);
                }
            });
        }
    }

    @Override
    public void setAsFavorite(final String id, final boolean isFavorite, final onSaveCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Check if storage type is already existing
                SavedProfileFavorites storage = realm.where(SavedProfileFavorites.class)
                        .equalTo("id", String.valueOf(id), Case.INSENSITIVE)
                        .findFirst();

                if (storage == null) {
                    // Does not exist yet
                    storage = realm.createObject(SavedProfileFavorites.class, id);
                    storage.setAsFavorite(isFavorite);
                } else {
                    // update
                    storage.setAsFavorite(isFavorite);
                }

                // Update all applications
                realm.insertOrUpdate(storage);
                if (callback != null) callback.onSuccess(isFavorite);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Profile.class).count();
    }
}

package com.ph.idisciple.idiscipleapp.data.local.repository.impl;


import com.ph.idisciple.idiscipleapp.data.local.model.ProfileObject;
import com.ph.idisciple.idiscipleapp.data.local.repository.IProfileRepository;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Charlene Chiang on 16/11/2017.
 * Contains interaction to ProfileObject Table
 */
public class ProfileRepository implements IProfileRepository {

    private Realm realm;

    public ProfileRepository() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void getKeyItem(final ProfileObject.ProfileType type, final onGetKeyItemCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ProfileObject storage = realm.where(ProfileObject.class)
                        .equalTo(ProfileObject.FIELD_ITEM, type.name())
                        .findFirst();

                if (storage != null && callback != null)
                    callback.onSuccess(realm.copyFromRealm(storage));
            }
        });
    }

    @Override
    public void saveKeyItem(final ProfileObject.ProfileType type, final String value, final onSaveCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Check if storage type is already existing
                ProfileObject storage = realm.where(ProfileObject.class)
                        .equalTo(ProfileObject.FIELD_ITEM, type.name(), Case.INSENSITIVE)
                        .findFirst();

                if (storage == null) {
                    // Does not exist yet
                    storage = realm.createObject(ProfileObject.class);
                    storage.setItemName(type.name());
                    storage.setItemValue(value);
                } else {
                    // update
                    storage.setItemValue(value);
                }

                // Update all applications
                realm.insertOrUpdate(storage);
                if (callback != null) callback.onSuccess();
            }
        });
    }

    @Override
    public void resetStorage(final onSaveCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ProfileObject> storage = realm.where(ProfileObject.class).findAll();
                storage.deleteAllFromRealm();
                if (callback != null) callback.onSuccess();
            }
        });
    }
}

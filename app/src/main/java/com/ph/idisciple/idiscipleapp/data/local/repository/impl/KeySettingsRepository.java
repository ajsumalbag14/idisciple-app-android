package com.ph.idisciple.idiscipleapp.data.local.repository.impl;

import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Charlene Chiang on 16/11/2017.
 * Contains interaction to KeySettings Table
 */
public class KeySettingsRepository implements IKeySettingsRepository {

    private Realm realm;

    public KeySettingsRepository() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void getKeyItem(final KeySettings.ItemType type, final onGetKeyItemCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                KeySettings storage = realm.where(KeySettings.class)
                        .equalTo(KeySettings.FIELD_ITEM, type.name())
                        .findFirst();

                if (storage != null && callback != null)
                    callback.onSuccess(realm.copyFromRealm(storage));
            }
        });
    }

    @Override
    public void saveKeyItem(final KeySettings.ItemType type, final String value, final onSaveCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Check if storage type is already existing
                KeySettings storage = realm.where(KeySettings.class)
                        .equalTo(KeySettings.FIELD_ITEM, type.name(), Case.INSENSITIVE)
                        .findFirst();

                if (storage == null) {
                    // Does not exist yet
                    storage = realm.createObject(KeySettings.class);
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
                RealmResults<KeySettings> storage = realm.where(KeySettings.class).findAll();
                storage.deleteAllFromRealm();
                if (callback != null) callback.onSuccess();
            }
        });
    }
}

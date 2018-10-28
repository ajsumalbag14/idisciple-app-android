package com.ph.idisciple.idiscipleapp.data.local.repository;

import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;

/**
 * Created by Charlene Chiang on 24/05/2018.
 */

public interface IKeySettingsRepository {

    interface onSaveCallback {
        void onSuccess();
    }

    interface onGetKeyItemCallback {
        void onSuccess(KeySettings keySettingItem);
    }

    void getKeyItem(KeySettings.ItemType type, onGetKeyItemCallback callback);

    void saveKeyItem(KeySettings.ItemType type, String value, onSaveCallback callback);

    void resetStorage(onSaveCallback callback);
}

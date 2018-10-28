package com.ph.idisciple.idiscipleapp.data.local.repository;

import com.ph.idisciple.idiscipleapp.data.local.model.ProfileObject;

/**
 * Created by Charlene Chiang on 24/05/2018.
 */

public interface IProfileRepository {

    interface onSaveCallback {
        void onSuccess();
    }

    interface onGetKeyItemCallback {
        void onSuccess(ProfileObject keySettingItem);
    }

    void getKeyItem(ProfileObject.ProfileType type, onGetKeyItemCallback callback);

    void saveKeyItem(ProfileObject.ProfileType type, String value, onSaveCallback callback);

    void resetStorage(onSaveCallback callback);
}

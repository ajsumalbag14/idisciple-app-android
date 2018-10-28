package com.ph.idisciple.idiscipleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * Created by Charlene Chiang on 16/11/2017.
 */

public class PreferenceUtils {

    private static final String PREF_REALM_INITIALIZED = "pref_r_initialized";
    private static final String PREF_REALM_CONFIGURED = "pref_r_configured";

    private static PreferenceUtils INSTANCE;

    private final SharedPreferences mPreferences;

    private PreferenceUtils(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized PreferenceUtils getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferenceUtils(context.getApplicationContext());
        }
        return INSTANCE;
    }
    
    public void setRealmInitialized(boolean shown) {
        mPreferences.edit()
                .putBoolean(PREF_REALM_INITIALIZED, shown)
                .apply();
    }

    public boolean isRealmAlreadyInitialized() {
        return mPreferences.getBoolean(PREF_REALM_INITIALIZED, false);
    }

    public void setRealmConfigured(boolean shown) {
        mPreferences.edit()
                .putBoolean(PREF_REALM_CONFIGURED, shown)
                .apply();
    }

    public boolean isRealmAlreadyConfigured() {
        return mPreferences.getBoolean(PREF_REALM_CONFIGURED, false);
    }

}

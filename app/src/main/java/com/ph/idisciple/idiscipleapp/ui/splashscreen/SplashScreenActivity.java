package com.ph.idisciple.idiscipleapp.ui.splashscreen;

import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.login.LoginScreenActivity;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;
import com.ph.idisciple.idiscipleapp.utils.PreferenceUtils;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Enumeration;

import javax.security.auth.x500.X500Principal;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashScreenActivity extends BaseActivity {

    private KeyStore keyStore;
    private KeySettingsRepository mKeySettingsRepository;
    private boolean isLoggedIn = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKeySettingsRepository = new KeySettingsRepository();
        checkIfRealmIsConfigured();
    }

    /* Realm Initialized */
    private void checkIfRealmIsConfigured() {
        if (!PreferenceUtils.getInstance(SplashScreenActivity.this).isRealmAlreadyInitialized())
            setUpRealmConfiguration();
        else {

            mKeySettingsRepository.getKeyItem(KeySettings.ItemType.IS_LOGGED_IN, new IKeySettingsRepository.onGetKeyItemCallback() {
                @Override
                public void onSuccess(KeySettings keySettingItem) {
                    String result = keySettingItem.getItemValue();
                    isLoggedIn = (result != null ? Boolean.parseBoolean(result) : false);

                    if (isLoggedIn)
                        showMainScreen();
                    else
                        showLoginOptionsScreen();
                }
            });

        }
    }

    private void setUpRealmConfiguration() {
        // Get KeyStore
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
        } catch (Exception e) {
        }

        String keyFromKeystore = "";
        try {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                keyFromKeystore = aliases.nextElement();
            }
        } catch (Exception e) {
        }

        if (!TextUtils.isEmpty(keyFromKeystore)) {

            // KeyStore is already initialized
            byte[] keyPreferenceByte = Base64.decode(keyFromKeystore, Base64.NO_WRAP);
            initializeRealm(keyPreferenceByte);

        } else {

            // Generate Random Key
            byte[] key = new byte[64];
            new SecureRandom().nextBytes(key);
            String generatedKey = Base64.encodeToString(key, Base64.NO_WRAP);

            try {

                // Initialize KeyStore
                keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);

                if (!keyStore.containsAlias(generatedKey)) {
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    end.add(Calendar.YEAR, 10);
                    KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(SplashScreenActivity.this)
                            .setAlias(generatedKey)
                            .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(start.getTime())
                            .setEndDate(end.getTime())
                            .build();
                    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    generator.initialize(spec);

                    KeyPair keyPair = generator.generateKeyPair();
                    initializeRealm(key);

                }
            } catch (Exception e) {
                // TODO: Generic Error
            }

        }
    }

    private void initializeRealm(byte[] key) {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .directory(getExternalFilesDir(null))
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(key)
                .build();
        Realm.setDefaultConfiguration(config);
        PreferenceUtils.getInstance(SplashScreenActivity.this).setRealmInitialized(true);
        initializeRealmDefaultData();
    }

    /**
     * Initializes Realm's default data
     */
    private void initializeRealmDefaultData() {
        if (!PreferenceUtils.getInstance(SplashScreenActivity.this).isRealmAlreadyConfigured()) {

            mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.IS_LOGGED_IN, "false", new IKeySettingsRepository.onSaveCallback() {
                @Override
                public void onSuccess() {
                    // Save for reference only
                    PreferenceUtils.getInstance(SplashScreenActivity.this).setRealmConfigured(true);
                    showLoginOptionsScreen();
                }
            });

        } else
            showLoginOptionsScreen();
    }

    private void showLoginOptionsScreen() {
        redirectToAnotherScreenAsFirstScreen(LoginScreenActivity.class);
    }

    private void showMainScreen() {
        redirectToAnotherScreenAsFirstScreen(MainAppScreenActivity.class);
    }

}

package com.ph.idisciple.idiscipleapp.ui.splashscreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
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

    private final String APP_VERSION = "app_version";
    private final String APP_LINK = "app_link";
    private final String APP_FORCE = "app_force";

    // Remote Config keys
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
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
        FirebaseApp.initializeApp(this);
        mKeySettingsRepository = new KeySettingsRepository();
        checkIfAppIsUpToDate();
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectToAnotherScreenAsFirstScreen(LoginScreenActivity.class);
            }
        }, 500); // Added delay for update
    }

    private void showMainScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectToAnotherScreenAsFirstScreen(MainAppScreenActivity.class);
            }
        }, 500); // Added delay for update
    }

    public void checkIfAppIsUpToDate() {

        long cacheExpiration = 0; //3600; // 1 hour in seconds.
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(SplashScreenActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mFirebaseRemoteConfig.activateFetched();
                        }

                        PackageInfo pInfo = null;
                        try {
                            boolean isForceUpate = mFirebaseRemoteConfig.getBoolean(APP_FORCE);
                            String appLink = mFirebaseRemoteConfig.getString(APP_LINK);
                            String fireBaseAppVersion = mFirebaseRemoteConfig.getString(APP_VERSION);

                            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                            String appVersion = pInfo.versionName;

                            if (isForceUpate) {
                                if (!TextUtils.equals(appVersion, fireBaseAppVersion)) {
                                    showUpdateDialog(appLink);
                                } else {
                                    checkIfRealmIsConfigured();
                                }
                            } else {
                                checkIfRealmIsConfigured();
                            }

                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                            getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
                        }

                    }
                });

    }

    public void showUpdateDialog(final String appLink) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_force_update_title))
                .setMessage(getString(R.string.dialog_force_update_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.button_update),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appLink));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }).create();
        dialog.show();
    }

}

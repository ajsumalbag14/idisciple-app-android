package com.ph.idisciple.idiscipleapp.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.ph.idisciple.idiscipleapp.utils.ShowMessageUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private ShowMessageUtil mShowMessageUtil;

    public final String DEVICE_TYPE = "android";
    public final String DEVICE_NAME = Build.MANUFACTURER + " " + Build.MODEL; // e.g. Xiaomi Mi5
    public String mDeviceId;
    public int mDisplayWidth;

    protected abstract int getLayout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        mShowMessageUtil = new ShowMessageUtil(this);
        mDeviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mDisplayWidth = displayMetrics.widthPixels;

    }

    public ShowMessageUtil getShowMessageUtil() {
        return mShowMessageUtil;
    }

    public void redirectToAnotherScreen(Class className) {
        Intent showIntent = new Intent(BaseActivity.this, className);
        startActivity(showIntent);
    }

    public void redirectToAnotherScreen(Class className, Bundle bundleToInclude) {
        Intent showIntent = new Intent(BaseActivity.this, className);
        if (bundleToInclude != null) showIntent.putExtras(bundleToInclude);
        startActivity(showIntent);
    }

    public void redirectToAnotherScreenAsFirstScreen(Class className) {
        Intent showIntent = new Intent(BaseActivity.this, className);
        showIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(showIntent);
    }

    public void redirectToAnotherScreenAsFirstScreen(Class className, Bundle bundleToInclude) {
        Intent showIntent = new Intent(BaseActivity.this, className);
        showIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (bundleToInclude != null) showIntent.putExtras(bundleToInclude);
        startActivity(showIntent);
    }

    public void redirectToAnotherScreenWithResult(Class className, Bundle bundleToInclude, int requestCode) {
        Intent showIntent = new Intent(BaseActivity.this, className);
        if (bundleToInclude != null) showIntent.putExtras(bundleToInclude);
        startActivityForResult(showIntent, requestCode);
    }



    /**
     * Shows a fragment from back stack if it exists.
     * Otherwise, create new instance from the specified fragment.
     *
     * @param fragmentManager Fragment Manager
     * @param containerViewId Layout resource id for Fragment
     * @param fragment        Fragment you want to show in the <code>containerViewId</> layout
     * @param addToBackStack  boolean if you want to add the fragment to back stack
     * @param bundleToInclude Bundle to pass to the next Fragment
     */
    public void showFragment(FragmentManager fragmentManager,
                             @IdRes int containerViewId,
                             Fragment fragment,
                             boolean addToBackStack,
                             Bundle bundleToInclude) {
        if (fragment != null) {

            String backStackName = fragment.getClass().getSimpleName();
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(backStackName);
            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (fragmentByTag == null)
                fragmentByTag = fragment;
            else
                ft.remove(fragmentByTag);

            if (bundleToInclude != null)
                fragmentByTag.setArguments(bundleToInclude);

            try {
                ft.replace(containerViewId, fragmentByTag, backStackName);
                if (addToBackStack) ft.addToBackStack(backStackName);
                ft.commit();
            } catch (Exception ex) {

            }

            fragmentManager.executePendingTransactions();
        }
    }

}

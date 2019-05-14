package com.ph.idisciple.idiscipleapp.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.ph.idisciple.idiscipleapp.R;
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

    /* Show / Hide Loading Dialog */
    public AlertDialog loadingDialog;
    public void hideLoadingDialog(){
        if(loadingDialog != null) loadingDialog.dismiss();
    }

    public void showLoadingDialog(){
        loadingDialog = getShowMessageUtil().showLoadingDialog();

        // Initialize a new window manager layout parameters
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(loadingDialog.getWindow().getAttributes());

        // Set alert dialog width equal to screen width 70%
        int displayWidth = mDisplayWidth;
        int dialogWindowWidth = (int) (displayWidth * 0.5f);
        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        // Apply the newly created layout parameters to the alert dialog window
        loadingDialog.getWindow().setAttributes(layoutParams);
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



    public int getDrawableCountryRes(String country) {
        int drawablePlaceholderRes = R.drawable.img_avatar_06_philippines;
        if (country != null) {
            try {
                switch (Integer.parseInt(country)) {
                    case 117:
                        drawablePlaceholderRes = R.drawable.img_avatar_00_korea;
                        break;
                    case 110:
                        drawablePlaceholderRes = R.drawable.img_avatar_01_japan;
                        break;
                    case 217:
                        drawablePlaceholderRes = R.drawable.img_avatar_02_taiwan;
                        break;
                    case 129:
                        drawablePlaceholderRes = R.drawable.img_avatar_03_macau;
                        break;
                    case 98:
                        drawablePlaceholderRes = R.drawable.img_avatar_04_hongkong;
                        break;
                    case 102:
                        drawablePlaceholderRes = R.drawable.img_avatar_05_indonesia;
                        break;
                    case 178:
                        drawablePlaceholderRes = R.drawable.img_avatar_06_philippines;
                        break;
                    case 201:
                        drawablePlaceholderRes = R.drawable.img_avatar_07_singapore;
                        break;
                    case 133:
                        drawablePlaceholderRes = R.drawable.img_avatar_08_malaysia;
                        break;
                    case 101:
                        drawablePlaceholderRes = R.drawable.img_avatar_09_india;
                        break;
                    case 19:
                        drawablePlaceholderRes = R.drawable.img_avatar_10_bangladesh;
                        break;
                    case 158:
                        drawablePlaceholderRes = R.drawable.img_avatar_11_nepal;
                        break;
                    case 209:
                        drawablePlaceholderRes = R.drawable.img_avatar_12_srilanka;
                        break;
                    case 220:
                        drawablePlaceholderRes = R.drawable.img_avatar_13_thailand;
                        break;
                    case 150:
                        drawablePlaceholderRes = R.drawable.img_avatar_14_myanmar;
                        break;
                    case 37:
                        drawablePlaceholderRes = R.drawable.img_avatar_15_cambodia;
                        break;
                    case 241:
                        drawablePlaceholderRes = R.drawable.img_avatar_16_vietnam;
                        break;
                    case 14:
                        drawablePlaceholderRes = R.drawable.img_avatar_17_australia;
                        break;
                    case 162:
                        drawablePlaceholderRes = R.drawable.img_avatar_18_newzealand;
                        break;
                    case 72:
                        drawablePlaceholderRes = R.drawable.img_avatar_19_fiji;
                        break;
                }
            } catch (Exception ex){

            }
        }
        return drawablePlaceholderRes;
    }

    public void tagActivityAsDialog(){
        // Enable Android 9.0 + to show dialog for Activity
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY; //Android P中AlertDialog要顯示在最上層
        } else {
            lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
        }
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }
}

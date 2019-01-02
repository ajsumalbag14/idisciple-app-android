package com.ph.idisciple.idiscipleapp.ui.mainappscreen;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;

public class MainAppScreenActivity extends BaseActivity implements MainAppScreenContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottomNavigation)
    BottomNavigationViewEx bottomNavigationView;


    private BaseFragment fragmentActive = null;
    private MainAppScreenContract.Presenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainAppScreenPresenter(MainAppScreenActivity.this, this);


        // Remove Animation & Shifting to BottomNavigationView
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setCurrentItem(0);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String name = menuItem.getTitle().toString();

        int order = menuItem.getOrder();
        BottomNavigationItemView bottomNavigationItemView = bottomNavigationView.getBottomNavigationItemView(order);
        bottomNavigationItemView.setTag(name);

//        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorText));
//        int[] colorIcon;
//        int[] colorText;
//        ColorStateList cslScheduleIcon;
//        ColorStateList cslScheduleText;

        switch (menuItem.getItemId()) {
            case R.id.menuSchedule:
//                colorIcon = new int[] {
//                        Color.WHITE,  // enabled
//                        getResources().getColor(R.color.colorIDiscipleRed), // disabled
//                        getResources().getColor(R.color.colorIDiscipleRed), // unchecked
//                        Color.WHITE// pressed
//                };
//                cslScheduleIcon = new ColorStateList(new int[][]{new int[0]}, new int[]{colorIcon[0]});
//                bottomNavigationView.setIconTintList(0, cslScheduleIcon);
//
//                colorText = new int[] {
//                        Color.WHITE,  // enabled
//                        getResources().getColor(R.color.colorIDiscipleRed), // disabled
//                        getResources().getColor(R.color.colorIDiscipleRed), // unchecked
//                        Color.WHITE// pressed
//                };
//                cslScheduleText = new ColorStateList(new int[][]{new int[0]}, new int[]{colorText[0]});
//                bottomNavigationView.setTextTintList(0, cslScheduleText);
//
//                bottomNavigationItemView.setItemBackground(getDrawable(R.drawable.shape_button_red_flat));

//                getFragmentManager().beginTransaction().hide(fragmentActive).show(fragmentTabMap).commit();
//                fragmentActive = fragmentTabMap;
//                onRefreshButtonClick(); // Refresh every time user selects Map Tab
                break;
            case R.id.menuCommunity:
//                getFragmentManager().beginTransaction().hide(fragmentActive).show(fragmentTabList).commit();
//                fragmentActive = fragmentTabList;
//                if(!isSpeechToTextFromMap) onRefreshButtonClick(); // Refresh every time user selects List Tab
                break;
            case R.id.menuSpeakers:
//                getFragmentManager().beginTransaction().hide(fragmentActive).show(fragmentTabFavorites).commit();
//                fragmentActive = fragmentTabFavorites;
                break;
            case R.id.menuWorkshops:
//                getFragmentManager().beginTransaction().hide(fragmentActive).show(fragmentTabAccount).commit();
//                fragmentActive = fragmentTabAccount;
//                mPresenter.fetchProfile();
                break;
            case R.id.menuMore:

                break;
        }

//        refreshedListApplyToTabs();

        return false;
    }

    @Override
    public void onFetchDataFailed(String errorMessage) {

    }

    @Override
    public void onFetchDataSuccess() {

    }

    @Override
    public void showNoInternetConnection() {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_no_internet), getString(R.string.dialog_error_message_no_internet));
    }

    @Override
    public void showTimeoutError() {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_timeout), getString(R.string.dialog_error_message_no_internet));
    }

    @Override
    public void showGenericError() {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
    }
}

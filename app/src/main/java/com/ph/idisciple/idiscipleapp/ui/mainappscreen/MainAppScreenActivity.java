package com.ph.idisciple.idiscipleapp.ui.mainappscreen;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment.SpeakerFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment.WorkshopFragment;

import butterknife.BindView;

import static com.ph.idisciple.idiscipleapp.ui.BaseFragment.newInstance;

public class MainAppScreenActivity extends BaseActivity implements MainAppScreenContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    final int[][] states = new int[][]{
            new int[]{android.R.attr.state_enabled}, // enabled
            new int[]{android.R.attr.state_pressed}  // pressed
    };
    final int[][] statesDefault = new int[][]{
            new int[]{-android.R.attr.state_enabled}, // disabled
            new int[]{-android.R.attr.state_checked} // unchecked
    };
    @BindView(R.id.rlToolbar) RelativeLayout rlToolbar;
    @BindView(R.id.bottomNavigation)
    BottomNavigationViewEx bottomNavigationView;
    /* BottomNavigationViewEx Colors */
    int[] colorDefault;
    int[] colorSpeaker;
    int[] colorWorkshops;
    int[] colorSchedule;
    int[] colorCommunity;
    int[] colorMore;
    ColorStateList cslDefault;
    ColorStateList cslSpeaker;
    ColorStateList cslWorkshops;
    ColorStateList cslSchedule;
    ColorStateList cslCommunity;
    ColorStateList cslMore;
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
        showLoadingDialog();
        mPresenter.fetchData();
        prepareColorStateList();
        prepareBottomNavigationBar();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        resetBottomNavigationView();
        String name = menuItem.getTitle().toString();

        int order = menuItem.getOrder();
        BottomNavigationItemView bottomNavigationItemView = bottomNavigationView.getBottomNavigationItemView(order);
        bottomNavigationItemView.setTag(name);

        switch (menuItem.getItemId()) {

            case R.id.menuSpeakers:
                fragmentActive = newInstance(SpeakerFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleOrange));
                bottomNavigationItemView.setIconTintList(cslSpeaker);
                bottomNavigationItemView.setTextColor(cslSpeaker);
                break;
            case R.id.menuWorkshops:
                fragmentActive = newInstance(WorkshopFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bottomNavigationItemView.setIconTintList(cslWorkshops);
                bottomNavigationItemView.setTextColor(cslWorkshops);
                break;
            case R.id.menuSchedule:
                fragmentActive = newInstance(SpeakerFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleRed));
                bottomNavigationItemView.setIconTintList(cslSchedule);
                bottomNavigationItemView.setTextColor(cslSchedule);
                break;
            case R.id.menuCommunity:
                fragmentActive = newInstance(SpeakerFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                bottomNavigationItemView.setIconTintList(cslCommunity);
                bottomNavigationItemView.setTextColor(cslCommunity);
                break;
            case R.id.menuMore:
                fragmentActive = newInstance(SpeakerFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorDividerGray));
                bottomNavigationItemView.setIconTintList(cslMore);
                bottomNavigationItemView.setTextColor(cslMore);
                break;
        }
        showFragment(getSupportFragmentManager(), R.id.flContainter, fragmentActive, false, null);
        return false;
    }

    private void prepareColorStateList() {

        colorDefault = new int[]{
                getResources().getColor(R.color.colorDividerGray),   // disabled
                getResources().getColor(R.color.colorDividerGray)   // unchecked
        };

        colorSpeaker = new int[]{
                getResources().getColor(R.color.colorIDiscipleOrange), // enabled
                getResources().getColor(R.color.colorIDiscipleOrange)  // pressed
        };

        colorWorkshops = new int[]{
                getResources().getColor(R.color.colorPrimary), // enabled
                getResources().getColor(R.color.colorPrimary)  // pressed
        };

        colorSchedule = new int[]{
                getResources().getColor(R.color.colorIDiscipleRed), // enabled
                getResources().getColor(R.color.colorIDiscipleRed)  // pressed
        };

        colorCommunity = new int[]{
                getResources().getColor(R.color.colorIDiscipleBlue), // enabled
                getResources().getColor(R.color.colorIDiscipleBlue)  // pressed
        };

        colorMore = new int[]{
                getResources().getColor(R.color.colorText),   // enabled
                getResources().getColor(R.color.colorText)   // pressed
        };

        cslDefault = new ColorStateList(statesDefault, colorDefault);
        cslSpeaker = new ColorStateList(states, colorSpeaker);
        cslCommunity = new ColorStateList(states, colorCommunity);
        cslSchedule = new ColorStateList(states, colorSchedule);
        cslWorkshops = new ColorStateList(states, colorWorkshops);
        cslMore = new ColorStateList(states, colorMore);
    }

    private void prepareBottomNavigationBar() {

        // Remove Animation & Shifting to BottomNavigationView
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(0, false);
        bottomNavigationView.enableShiftingMode(1, false);
        bottomNavigationView.enableShiftingMode(2, false);
        bottomNavigationView.enableShiftingMode(3, false);
        bottomNavigationView.enableShiftingMode(4, false);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setItemHorizontalTranslationEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        resetBottomNavigationView();
        bottomNavigationView.setCurrentItem(0);

    }

    private void resetBottomNavigationView() {
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true); //hack because it won't be unselected
        bottomNavigationView.setIconTintList(0, cslDefault);
        bottomNavigationView.setTextTintList(0, cslDefault);

        bottomNavigationView.setIconTintList(1, cslDefault);
        bottomNavigationView.setTextTintList(1, cslDefault);

        bottomNavigationView.setIconTintList(2, cslDefault);
        bottomNavigationView.setTextTintList(2, cslDefault);

        bottomNavigationView.setIconTintList(3, cslDefault);
        bottomNavigationView.setTextTintList(3, cslDefault);

        bottomNavigationView.setIconTintList(4, cslDefault);
        bottomNavigationView.setTextTintList(4, cslDefault);
    }

    @Override
    public void onFetchDataFailed(String errorMessage) {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
        hideLoadingDialog();
    }

    @Override
    public void onFetchDataSuccess() {
        hideLoadingDialog();
    }

    @Override
    public void showNoInternetConnection() {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_no_internet), getString(R.string.dialog_error_message_no_internet));
        hideLoadingDialog();
    }

    @Override
    public void showTimeoutError() {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_timeout), getString(R.string.dialog_error_message_no_internet));
        hideLoadingDialog();
    }

    @Override
    public void showGenericError() {
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
        hideLoadingDialog();
    }
}

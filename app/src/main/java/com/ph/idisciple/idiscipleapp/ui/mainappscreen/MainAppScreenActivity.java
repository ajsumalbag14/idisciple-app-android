package com.ph.idisciple.idiscipleapp.ui.mainappscreen;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Country;
import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;
import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.CommunityFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog.YourProfileDialogFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.MoreFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.schedulefragment.ScheduleFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment.SpeakerFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.viewmap.ViewMapActivity;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment.WorkshopFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ph.idisciple.idiscipleapp.ui.BaseFragment.newInstance;
import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class MainAppScreenActivity extends BaseActivity implements MainAppScreenContract.View, BottomNavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    final int[][] states = new int[][]{
            new int[]{android.R.attr.state_enabled}, // enabled
            new int[]{android.R.attr.state_pressed}  // pressed
    };
    final int[][] statesDefault = new int[][]{
            new int[]{-android.R.attr.state_enabled}, // disabled
            new int[]{-android.R.attr.state_checked} // unchecked
    };

    public MainAppScreenPresenter mPresenter;
    public String mUserId;
    public String mCountryId;

    @BindView(R.id.rlToolbar) RelativeLayout rlToolbar;
    @BindView(R.id.ivToolbarMenuProfile) ImageView ivToolbarMenuProfile;
    @BindView(R.id.bottomNavigation)
    BottomNavigationViewEx bottomNavigationView;

    @BindView(R.id.srlSwipeToRefresh) SwipeRefreshLayout srlSwipeToRefresh;
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
    int mCurrentSelectedBottomNav = -1;
    private BaseFragment fragmentActive = null;
    private Bundle bundleToInclude;

    @Override
    protected int getLayout() {
        return R.layout.activity_main_screen;
    }

    @OnClick(R.id.ivToolbarMenuInfo)
    public void onToolbarInfoClick() {
        redirectToAnotherScreen(ViewMapActivity.class, bundleToInclude);
    }

    @OnClick(R.id.ivToolbarMenuProfile)
    public void onToolbarProfileClick() {
        YourProfileDialogFragment fragmentYourProfileDialog = YourProfileDialogFragment.newInstance(bundleToInclude);
        FragmentManager fm = getSupportFragmentManager();
        fragmentYourProfileDialog.show(fm, "show_your_profile_fragment");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainAppScreenPresenter( this);
        bundleToInclude = new Bundle();

        // Fetch Data
        srlSwipeToRefresh.setOnRefreshListener(this);
        srlSwipeToRefresh.setColorScheme(R.color.colorIDiscipleBlue,
                R.color.colorIDiscipleOrange,
                R.color.colorIDiscipleRed,
                R.color.colorPrimary);
        onRefresh();

        prepareColorStateList();
        prepareBottomNavigationBar();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.checkIfEventWillBeHappeningSoon();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
                fragmentActive = newInstance(ScheduleFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleRed));
                bottomNavigationItemView.setIconTintList(cslSchedule);
                bottomNavigationItemView.setTextColor(cslSchedule);
                break;
            case R.id.menuCommunity:
                fragmentActive = newInstance(CommunityFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                bottomNavigationItemView.setIconTintList(cslCommunity);
                bottomNavigationItemView.setTextColor(cslCommunity);
                break;
            case R.id.menuMore:
                fragmentActive = newInstance(MoreFragment.class);
                rlToolbar.setBackgroundColor(getResources().getColor(R.color.colorDividerGray));
                bottomNavigationItemView.setIconTintList(cslMore);
                bottomNavigationItemView.setTextColor(cslMore);
                break;
        }

        // Avoid double selection of same tab
        if (mCurrentSelectedBottomNav != order) {
            showFragment(getSupportFragmentManager(), R.id.flContainter, fragmentActive, false, null);
            mCurrentSelectedBottomNav = order;
        }

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
        bottomNavigationView.setCurrentItem(2); // set Schedule as first tab

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
        onFetchDataSuccess();
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
    }

    @Override
    public void onFetchDataSuccess() {
        hideLoadingDialog();
        srlSwipeToRefresh.setRefreshing(false);
    }

    @Override
    public void setUserId(String userId) {
        mUserId = userId;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateAvatar(RefreshAvatarEvent event){
        prepareBundleToPassInPrepForViewOwnProfile();
        setProfileAvatar(event.avatarImageUrl, mCountryId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEnableDisableSwipeRefreshLayout(EnableDisableSwipeRefreshLayout event){
        srlSwipeToRefresh.setEnabled(event.isEnableSwipeRefreshLayout);
    }

    @Override
    public void prepareBundleToPassInPrepForViewOwnProfile() {
        bundleToInclude = new Bundle();
        List<Country> mCountryList = mPresenter.mCountryRepository.getContentList();
        List<FamilyGroup> mFamilyGroupList = mPresenter.mFamilyGroupRepository.getContentList();
        List<Workshop> mWorkshopList = mPresenter.mWorkshopRepository.getContentList();
        List<Profile> AttendeesList = mPresenter.mAttendeesRepository.getContentList();

        Profile currentProfile = from(AttendeesList).where("getId", eq(mUserId)).first();
        if(currentProfile != null) {
            bundleToInclude.putString("avatar", currentProfile.getUserImageUrl());
            bundleToInclude.putString("fullname", currentProfile.getUserFullName());
            bundleToInclude.putString("nickname", currentProfile.getUserNickName());
            bundleToInclude.putString("id", currentProfile.getId());

            mCountryId = currentProfile.getUserCountry();
            if (mCountryId != null) {
                bundleToInclude.putString("countryId", mCountryId);
                Country country = from(mCountryList).where("getId", eq(mCountryId)).first();
                bundleToInclude.putString("countryName", country == null ? "" : country.getCountryName());
            }
            String familyGroupId = currentProfile.getUserFamilyGroupId();
            if (familyGroupId != null) {
                bundleToInclude.putString("familyGroupId", familyGroupId);
                FamilyGroup familyGroup = from(mFamilyGroupList).where("getId", eq(familyGroupId)).first();
                bundleToInclude.putString("familyGroupName", familyGroup == null ? "" : familyGroup.getFamilyGroupName());
            }

            String workshopId1 = currentProfile.getUserWorkshop1();
            if (workshopId1 != null) {
                bundleToInclude.putString("workshopId1", workshopId1);
                Workshop workshop = from(mWorkshopList).where("getId", eq(workshopId1)).first();
                bundleToInclude.putString("workshopId1Name", workshop == null ? "" : workshop.getWorkshopName());
            }

            String workshopId2 = currentProfile.getUserWorkshop2();
            if (workshopId2 != null) {
                bundleToInclude.putString("workshopId2", workshopId2);
                Workshop workshop = from(mWorkshopList).where("getId", eq(workshopId2)).first();
                bundleToInclude.putString("workshopId2Name", workshop == null ? "" : workshop.getWorkshopName());
            }
        }
    }

    @Override
    public void setProfileAvatar(String userAvatar, String countryId) {
        Glide.with(MainAppScreenActivity.this)
                .load(userAvatar)
                .apply( RequestOptions
                        .circleCropTransform()
                        .placeholder(getDrawableCountryRes(countryId))
                        .error(getDrawableCountryRes(countryId)))
                .into(ivToolbarMenuProfile);
    }

    @Override
    public void showHappeningNowDialog(Schedule scheduleItem) {
        HappeningSoonDialogFragment fragmentWorkshopInfoDialog = HappeningSoonDialogFragment.newInstance(scheduleItem);
        FragmentManager fm = getSupportFragmentManager();
        fragmentWorkshopInfoDialog.show(fm, "show_happening_now_fragment");
    }

    @Override
    public void showNoInternetConnection() {
        onFetchDataSuccess();
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_no_internet), getString(R.string.dialog_error_message_no_internet));
    }

    @Override
    public void showTimeoutError() {
        onFetchDataSuccess();
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_timeout), getString(R.string.dialog_error_message_no_internet));
    }

    @Override
    public void showGenericError() {
        onFetchDataSuccess();
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
    }

    @Override
    public void onRefresh() {
        showLoadingDialog();
        mPresenter.fetchData();
    }
}

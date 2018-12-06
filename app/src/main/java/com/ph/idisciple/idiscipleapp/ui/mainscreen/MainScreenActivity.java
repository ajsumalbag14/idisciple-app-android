package com.ph.idisciple.idiscipleapp.ui.mainscreen;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.mainscreen.speakerscreen.SpeakerFragment;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreenActivity extends AppCompatActivity {

    @BindDrawable(R.drawable.ic_nav_group_hide) Drawable drawableNavCollapsibleHideIcon;
    @BindDrawable(R.drawable.ic_nav_group_show) Drawable drawableNavCollapsibleShowIcon;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.tvToolbarTitle) TextView tvToolbarTitle;
    @BindView(R.id.ivToolbarNavMenu) ImageView ivToolbarNavMenu;

    @BindView(R.id.tvNavMenuSchedule) TextView tvNavMenuSchedule;
    @BindView(R.id.tvNavMenuSpeakers) TextView tvNavMenuSpeakers;
    @BindView(R.id.tvNavMenuWorkshops) TextView tvNavMenuWorkshops;
    @BindView(R.id.tvNavMenuCommunity) TextView tvNavMenuCommunity;
    @BindView(R.id.tvNavMenuResources) TextView tvNavMenuResources;
    @BindView(R.id.tvNavMenuVenueDirectory) TextView tvNavMenuVenueDirectory;
    @BindView(R.id.tvNavMenuSponsors) TextView tvNavMenuSponsors;
    @BindView(R.id.tvNavMenuInformation) TextView tvNavMenuInformation;
    @BindView(R.id.tvNavMenuTermsAndConditions) TextView tvNavMenuTermsAndConditions;

    @BindView(R.id.ivNavMenuDashboard) ImageView ivNavMenuDashboard;
    @BindView(R.id.ivNavMenuProfile) ImageView ivNavMenuProfile;
    @BindView(R.id.ivNavMenuEvent) ImageView ivNavMenuEvent;
    @BindView(R.id.ivNavMenuSocial) ImageView ivNavMenuSocial;
    @BindView(R.id.ivNavMenuAbout) ImageView ivNavMenuAbout;
    @BindView(R.id.ivNavMenuLogout) ImageView ivNavMenuLogout;

    @BindView(R.id.clNavMenuDashboard) ConstraintLayout clNavMenuDashboard;
    @BindView(R.id.clNavMenuProfile) ConstraintLayout clNavMenuProfile;
    @BindView(R.id.clNavMenuEvent) ConstraintLayout clNavMenuEvent;
    @BindView(R.id.clNavMenuSocial) ConstraintLayout clNavMenuSocial;
    @BindView(R.id.clNavMenuAbout) ConstraintLayout clNavMenuAbout;
    @BindView(R.id.clNavMenuLogout) ConstraintLayout clNavMenuLogout;

    @BindView(R.id.clNavMenuSchedule) ConstraintLayout clNavMenuSchedule;
    @BindView(R.id.clNavMenuSpeakers) ConstraintLayout clNavMenuSpeakers;
    @BindView(R.id.clNavMenuWorkshops) ConstraintLayout clNavMenuWorkshops;
    @BindView(R.id.clNavMenuCommunity) ConstraintLayout clNavMenuCommunity;
    @BindView(R.id.clNavMenuResources) ConstraintLayout clNavMenuResources;
    @BindView(R.id.clNavMenuVenueDirectory) ConstraintLayout clNavMenuVenueDirectory;
    @BindView(R.id.clNavMenuSponsors) ConstraintLayout clNavMenuSponsors;
    @BindView(R.id.clNavMenuInformation) ConstraintLayout clNavMenuInformation;
    @BindView(R.id.clNavMenuTermsAndConditions) ConstraintLayout clNavMenuTermsAndConditions;

    @BindView(R.id.llNavMenu2) LinearLayout llNavMenu2;
    @BindView(R.id.llNavMenu2Sub) LinearLayout llNavMenu2Sub;
    @BindView(R.id.ivNavEventMenuGroupCollapsible) ImageView ivNavEventMenuGroupCollapsible;
    private boolean isNavEventMenuGroupShown = false;

    public enum NavDrawerMenu {
        DASHBOARD, PROFILE, EVENT, SOCIAL, ABOUT, LOGOUT,
        SCHEDULE, SPEAKERS, WORKSHOPS, COMMUNITY, RESOURCES, VENUEDIRECTORY, SPONSORS, INFORMATION, TERMSANDCONDITIONS
    }
    private NavDrawerMenu selectedNavMenuItem = null;

    public View.OnClickListener onNavMenuOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment selectedNewFragment = new DashboardFragment();
            if(selectedNavMenuItem != null) resetNavigationDrawerSelection();

            switch (v.getId()) {
                case R.id.clNavMenuDashboard:
                    selectedNewFragment = Fragment.instantiate(MainScreenActivity.this, DashboardFragment.class.getName(), null);

                    clNavMenuDashboard.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleOrange));
                    ivNavMenuDashboard.setImageDrawable(getDrawable(R.drawable.ic_nav_dashboard_active));
                    selectedNavMenuItem = NavDrawerMenu.DASHBOARD;
                    break;
                case R.id.clNavMenuProfile:
                    clNavMenuProfile.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    ivNavMenuProfile.setImageDrawable(getDrawable(R.drawable.ic_nav_profile_active));
                    selectedNavMenuItem = NavDrawerMenu.PROFILE;
                    break;
                case R.id.clNavMenuEvent:
                    clNavMenuEvent.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    ivNavMenuEvent.setImageDrawable(getDrawable(R.drawable.ic_nav_event_active));
                    selectedNavMenuItem = NavDrawerMenu.EVENT;
                    break;
                case R.id.clNavMenuSocial:
                    clNavMenuSocial.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleRed));
                    ivNavMenuSocial.setImageDrawable(getDrawable(R.drawable.ic_nav_social_active));
                    selectedNavMenuItem = NavDrawerMenu.SOCIAL;
                    break;
                case R.id.clNavMenuAbout:
                    clNavMenuAbout.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleOrange));
                    ivNavMenuAbout.setImageDrawable(getDrawable(R.drawable.ic_nav_about_active));
                    selectedNavMenuItem = NavDrawerMenu.ABOUT;
                    break;
                case R.id.clNavMenuLogout:
                    clNavMenuLogout.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleGray));
                    ivNavMenuLogout.setImageDrawable(getDrawable(R.drawable.ic_nav_logout_active));
                    selectedNavMenuItem = NavDrawerMenu.LOGOUT;
                    break;

                case R.id.clNavMenuSchedule:
                    selectedNavMenuItem = NavDrawerMenu.SCHEDULE;
                    clNavMenuSchedule.setBackgroundColor(Color.WHITE);
                    tvNavMenuSchedule.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuSpeakers:
                    selectedNewFragment = Fragment.instantiate(MainScreenActivity.this, SpeakerFragment.class.getName(), null);

                    selectedNavMenuItem = NavDrawerMenu.SPEAKERS;
                    clNavMenuSpeakers.setBackgroundColor(Color.WHITE);
                    tvNavMenuSpeakers.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuWorkshops:
                    selectedNavMenuItem = NavDrawerMenu.WORKSHOPS;
                    clNavMenuWorkshops.setBackgroundColor(Color.WHITE);
                    tvNavMenuWorkshops.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuCommunity:
                    selectedNavMenuItem = NavDrawerMenu.COMMUNITY;
                    clNavMenuCommunity.setBackgroundColor(Color.WHITE);
                    tvNavMenuCommunity.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuResources:
                    selectedNavMenuItem = NavDrawerMenu.RESOURCES;
                    clNavMenuResources.setBackgroundColor(Color.WHITE);
                    tvNavMenuResources.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuVenueDirectory:
                    selectedNavMenuItem = NavDrawerMenu.VENUEDIRECTORY;
                    clNavMenuVenueDirectory.setBackgroundColor(Color.WHITE);
                    tvNavMenuVenueDirectory.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuSponsors:
                    selectedNavMenuItem = NavDrawerMenu.SPONSORS;
                    clNavMenuSponsors.setBackgroundColor(Color.WHITE);
                    tvNavMenuSponsors.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuInformation:
                    selectedNavMenuItem = NavDrawerMenu.INFORMATION;
                    clNavMenuInformation.setBackgroundColor(Color.WHITE);
                    tvNavMenuInformation.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuTermsAndConditions:
                    selectedNavMenuItem = NavDrawerMenu.TERMSANDCONDITIONS;
                    clNavMenuTermsAndConditions.setBackgroundColor(Color.WHITE);
                    tvNavMenuTermsAndConditions.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
            }

            replaceFragment(selectedNewFragment, false);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ivToolbarNavMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        initializeOnClickForDrawerMenus(navigationView);
        clNavMenuDashboard.performClick();

        drawer.bringToFront();
        drawer.requestLayout();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeOnClickForDrawerMenus(View navigationView) {
        clNavMenuEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isNavEventMenuGroupShown = !isNavEventMenuGroupShown;
                ivNavEventMenuGroupCollapsible.setImageDrawable(isNavEventMenuGroupShown ? drawableNavCollapsibleShowIcon : drawableNavCollapsibleHideIcon);

                llNavMenu2.setBackgroundColor(getResources().getColor(isNavEventMenuGroupShown ? R.color.colorText : R.color.colorIDiscipleBlue));
                ivNavMenuEvent.setImageDrawable(getDrawable(isNavEventMenuGroupShown ? R.drawable.ic_nav_event : R.drawable.ic_nav_event_active));
                llNavMenu2Sub.setVisibility(isNavEventMenuGroupShown ? View.GONE : View.VISIBLE);
            }
        });

        clNavMenuDashboard.setOnClickListener(onNavMenuOnClick);
        clNavMenuProfile.setOnClickListener(onNavMenuOnClick);
        clNavMenuSocial.setOnClickListener(onNavMenuOnClick);
        clNavMenuAbout.setOnClickListener(onNavMenuOnClick);
        clNavMenuLogout.setOnClickListener(onNavMenuOnClick);

        clNavMenuSchedule.setOnClickListener(onNavMenuOnClick);
        clNavMenuSpeakers.setOnClickListener(onNavMenuOnClick);
        clNavMenuWorkshops.setOnClickListener(onNavMenuOnClick);
        clNavMenuCommunity.setOnClickListener(onNavMenuOnClick);
        clNavMenuResources.setOnClickListener(onNavMenuOnClick);
        clNavMenuVenueDirectory.setOnClickListener(onNavMenuOnClick);
        clNavMenuSponsors.setOnClickListener(onNavMenuOnClick);
        clNavMenuInformation.setOnClickListener(onNavMenuOnClick);
        clNavMenuTermsAndConditions.setOnClickListener(onNavMenuOnClick);
    }

    private void resetNavigationDrawerSelection(){
        switch (selectedNavMenuItem) {
            case DASHBOARD:
                clNavMenuDashboard.setBackgroundColor(getResources().getColor(R.color.colorText));
                ivNavMenuDashboard.setImageDrawable(getDrawable(R.drawable.ic_nav_dashboard));
                selectedNavMenuItem = NavDrawerMenu.DASHBOARD;
                break;
            case PROFILE:
                clNavMenuProfile.setBackgroundColor(getResources().getColor(R.color.colorText));
                ivNavMenuProfile.setImageDrawable(getDrawable(R.drawable.ic_nav_profile));
                selectedNavMenuItem = NavDrawerMenu.PROFILE;
                break;
            case EVENT:
                clNavMenuEvent.setBackgroundColor(getResources().getColor(R.color.colorText));
                ivNavMenuEvent.setImageDrawable(getDrawable(R.drawable.ic_nav_event));
                selectedNavMenuItem = NavDrawerMenu.EVENT;
                break;
            case SOCIAL:
                clNavMenuSocial.setBackgroundColor(getResources().getColor(R.color.colorText));
                ivNavMenuSocial.setImageDrawable(getDrawable(R.drawable.ic_nav_social));
                selectedNavMenuItem = NavDrawerMenu.SOCIAL;
                break;
            case ABOUT:
                clNavMenuAbout.setBackgroundColor(getResources().getColor(R.color.colorText));
                ivNavMenuAbout.setImageDrawable(getDrawable(R.drawable.ic_nav_about));
                selectedNavMenuItem = NavDrawerMenu.ABOUT;
                break;
            case LOGOUT:
                clNavMenuLogout.setBackgroundColor(getResources().getColor(R.color.colorText));
                ivNavMenuLogout.setImageDrawable(getDrawable(R.drawable.ic_nav_logout));
                selectedNavMenuItem = NavDrawerMenu.LOGOUT;
                break;

            case SCHEDULE:
                clNavMenuSchedule.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuSchedule.setTextColor(Color.WHITE);
                break;
            case SPEAKERS:
                clNavMenuSpeakers.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuSpeakers.setTextColor(Color.WHITE);
                break;
            case WORKSHOPS:
                clNavMenuWorkshops.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuWorkshops.setTextColor(Color.WHITE);
                break;
            case COMMUNITY:
                clNavMenuCommunity.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuCommunity.setTextColor(Color.WHITE);
                break;
            case RESOURCES:
                clNavMenuResources.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuResources.setTextColor(Color.WHITE);
                break;
            case VENUEDIRECTORY:
                clNavMenuVenueDirectory.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuVenueDirectory.setTextColor(Color.WHITE);
                break;
            case SPONSORS:
                clNavMenuSponsors.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuSponsors.setTextColor(Color.WHITE);
                break;
            case INFORMATION:
                clNavMenuInformation.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuInformation.setTextColor(Color.WHITE);
                break;
            case TERMSANDCONDITIONS:
                clNavMenuTermsAndConditions.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                tvNavMenuTermsAndConditions.setTextColor(Color.WHITE);
                break;
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.flContent, fragment);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }

    public void redirectToAnotherScreen(Class className, Bundle bundleToInclude) {
        Intent showIntent = new Intent(MainScreenActivity.this, className);
        if (bundleToInclude != null) showIntent.putExtras(bundleToInclude);
        startActivity(showIntent);
    }

}

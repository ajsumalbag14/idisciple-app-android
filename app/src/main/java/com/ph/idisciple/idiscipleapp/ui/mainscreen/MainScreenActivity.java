package com.ph.idisciple.idiscipleapp.ui.mainscreen;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;

import butterknife.BindDrawable;

public class MainScreenActivity extends AppCompatActivity {

    @BindDrawable(R.drawable.ic_nav_group_hide) Drawable drawableNavCollapsibleHideIcon;
    @BindDrawable(R.drawable.ic_nav_group_show) Drawable drawableNavCollapsibleShowIcon;

    TextView tvNavMenuSchedule;
    TextView tvNavMenuSpeakers;
    TextView tvNavMenuWorkshops;
    TextView tvNavMenuCommunity;
    TextView tvNavMenuResources;
    TextView tvNavMenuVenueDirectory;
    TextView tvNavMenuSponsors;
    TextView tvNavMenuInformation;
    TextView tvNavMenuTermsAndConditions;

    ImageView ivNavMenuDashboard;
    TextView ivNavMenuProfile;
    TextView ivNavMenuEvent;
    TextView ivNavMenuSocial;
    TextView ivNavMenuAbout;
    TextView ivNavMenuLogout;

    ImageView ivNavMenuSchedule;
    TextView ivNavMenuSpeakers;
    TextView ivNavMenuWorkshops;
    TextView ivNavMenuCommunity;
    TextView ivNavMenuResources;
    TextView ivNavMenuVenueDirectory;
    TextView ivNavMenuSponsors;
    TextView ivNavMenuInformation;
    TextView ivNavMenuTermsAndConditions;

    ConstraintLayout clNavMenuDashboard;
    ConstraintLayout clNavMenuProfile;
    ConstraintLayout clNavMenuEvent;
    ConstraintLayout clNavMenuSocial;
    ConstraintLayout clNavMenuAbout;
    ConstraintLayout clNavMenuLogout;

    ConstraintLayout clNavMenuSchedule;
    ConstraintLayout clNavMenuSpeakers;
    ConstraintLayout clNavMenuWorkshops;
    ConstraintLayout clNavMenuCommunity;
    ConstraintLayout clNavMenuResources;
    ConstraintLayout clNavMenuVenueDirectory;
    ConstraintLayout clNavMenuSponsors;
    ConstraintLayout clNavMenuInformation;
    ConstraintLayout clNavMenuTermsAndConditions;

    public View.OnClickListener onNavMenuOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.clNavMenuDashboard:
                    clNavMenuDashboard.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleOrange));

                    break;
                case R.id.clNavMenuProfile:
                    clNavMenuProfile.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case R.id.clNavMenuEvent:
                    clNavMenuEvent.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuSocial:
                    clNavMenuSocial.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleRed));
                    break;
                case R.id.clNavMenuAbout:
                    clNavMenuAbout.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleOrange));
                    break;
                case R.id.clNavMenuLogout:
                    clNavMenuLogout.setBackgroundColor(getResources().getColor(R.color.colorIDiscipleGray));
                    break;

                case R.id.clNavMenuSchedule:
                    clNavMenuSchedule.setBackgroundColor(Color.WHITE);
                    tvNavMenuSchedule.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuSpeakers:
                    clNavMenuSpeakers.setBackgroundColor(Color.WHITE);
                    tvNavMenuSpeakers.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuWorkshops:
                    clNavMenuWorkshops.setBackgroundColor(Color.WHITE);
                    tvNavMenuWorkshops.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuCommunity:
                    clNavMenuCommunity.setBackgroundColor(Color.WHITE);
                    tvNavMenuCommunity.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuResources:
                    clNavMenuResources.setBackgroundColor(Color.WHITE);
                    tvNavMenuResources.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuVenueDirectory:
                    clNavMenuVenueDirectory.setBackgroundColor(Color.WHITE);
                    tvNavMenuVenueDirectory.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuSponsors:
                    clNavMenuSponsors.setBackgroundColor(Color.WHITE);
                    tvNavMenuSponsors.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuInformation:
                    clNavMenuInformation.setBackgroundColor(Color.WHITE);
                    tvNavMenuInformation.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
                case R.id.clNavMenuTermsAndConditions:
                    clNavMenuTermsAndConditions.setBackgroundColor(Color.WHITE);
                    tvNavMenuTermsAndConditions.setTextColor(getResources().getColor(R.color.colorIDiscipleBlue));
                    break;
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    };

    ImageView ivNavEventMenuGroupCollapsible;
    private boolean isNavEventMenuGroupShown = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        ivNavEventMenuGroupCollapsible = (ImageView) navigationView.findViewById(R.id.ivNavEventMenuGroupCollapsible);
        ivNavEventMenuGroupCollapsible.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isNavEventMenuGroupShown = !isNavEventMenuGroupShown;
                ivNavEventMenuGroupCollapsible.setImageDrawable(isNavEventMenuGroupShown ? drawableNavCollapsibleShowIcon : drawableNavCollapsibleHideIcon);
            }
        });

        clNavMenuDashboard = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuDashboard);
        clNavMenuProfile = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuProfile);
        clNavMenuEvent = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuEvent);
        clNavMenuSocial = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuSocial);
        clNavMenuAbout = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuAbout);
        clNavMenuLogout = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuLogout);

        clNavMenuSchedule = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuSchedule);
        clNavMenuSpeakers = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuSpeakers);
        clNavMenuWorkshops = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuWorkshops);
        clNavMenuCommunity = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuCommunity);
        clNavMenuResources = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuResources);
        clNavMenuVenueDirectory = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuVenueDirectory);
        clNavMenuSponsors = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuSponsors);
        clNavMenuInformation = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuInformation);
        clNavMenuTermsAndConditions = (ConstraintLayout) navigationView.findViewById(R.id.clNavMenuTermsAndConditions);

        tvNavMenuSchedule = (TextView) navigationView.findViewById(R.id.tvNavMenuSchedule);
        tvNavMenuSpeakers = (TextView) navigationView.findViewById(R.id.tvNavMenuSpeakers);
        tvNavMenuWorkshops = (TextView) navigationView.findViewById(R.id.tvNavMenuWorkshops);
        tvNavMenuCommunity = (TextView) navigationView.findViewById(R.id.tvNavMenuCommunity);
        tvNavMenuResources = (TextView) navigationView.findViewById(R.id.tvNavMenuResources);
        tvNavMenuVenueDirectory = (TextView) navigationView.findViewById(R.id.tvNavMenuVenueDirectory);
        tvNavMenuSponsors = (TextView) navigationView.findViewById(R.id.tvNavMenuSponsors);
        tvNavMenuInformation = (TextView) navigationView.findViewById(R.id.tvNavMenuInformation);
        tvNavMenuTermsAndConditions = (TextView) navigationView.findViewById(R.id.tvNavMenuTermsAndConditions);

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
}

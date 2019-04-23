package com.ph.idisciple.idiscipleapp.ui.mainappscreen.viewmap;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.JsonBuildingItemRaw;
import com.ph.idisciple.idiscipleapp.data.local.model.JsonBuildingRawList;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog.YourProfileInfoDialogActivity;
import com.ph.idisciple.idiscipleapp.widgets.CustomZoomageView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ViewMapActivity extends BaseActivity {

    @BindView(R.id.rvList) RecyclerView rvList;
    @BindView(R.id.ivZoomageView) CustomZoomageView ivZoomageView;
    @BindView(R.id.ivToolbarMenuProfile) ImageView ivToolbarMenuProfile;

    @OnClick(R.id.ivShowList)
    public void onShowListClick(){
        isListShown = !isListShown;
        rvList.setVisibility(isListShown ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.ivBackIcon)
    public void onBackIconClick(){
        onBackPressed();
    }

    @OnClick(R.id.tvBackText)
    public void onBackTextClick(){
        onBackPressed();
    }

    @OnClick(R.id.ivZoomInIcon)
    public void onZoomInClick(){
        MotionEvent event = MotionEvent.obtain(0L, 0L, MotionEvent.ACTION_DOWN, 577.17773f, 767.7539f, 0);
        ivZoomageView.dispatchTouchEvent(event);
        //MotionEvent { action=ACTION_DOWN, actionButton=0, id[0]=0, x[0]=392.60742, y[0]=668.56934, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=74267430, downTime=74267430, deviceId=5, source=0x1002 }
    }

    @OnClick(R.id.ivToolbarMenuProfile)
    public void onToolbarProfileClick() {
        redirectToAnotherScreen(YourProfileInfoDialogActivity.class, bundleToInclude);
    }

    private boolean isListShown = false;
    private Bundle bundleToInclude;

    @Override
    protected int getLayout() {
        return R.layout.activity_view_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ViewMapActivity.this);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        InputStream rawBuilding = getResources().openRawResource(R.raw.buildings);
        Reader rdBuilding = new BufferedReader(new InputStreamReader(rawBuilding));
        JsonBuildingRawList mJsonListBloodType = new Gson().fromJson(rdBuilding, JsonBuildingRawList.class);
        ArrayList<JsonBuildingItemRaw> mRawData = mJsonListBloodType.getList();

        MapLegendsAdapter mAdapter = new MapLegendsAdapter(ViewMapActivity.this, mRawData);
        rvList.setAdapter(mAdapter);

        bundleToInclude = getIntent().getExtras();

        Glide.with(ViewMapActivity.this)
                .load(bundleToInclude.getString("avatar", ""))
                .apply( RequestOptions
                        .circleCropTransform()
                        .placeholder(getDrawableCountryRes(bundleToInclude.getString("countryId", "0")))
                        .error(getDrawableCountryRes(bundleToInclude.getString("countryId", "0"))))
                .into(ivToolbarMenuProfile);
    }

    @Override
    public void onBackPressed(){
        if(isListShown){
            onShowListClick();
        } else
            finish();
    }
}

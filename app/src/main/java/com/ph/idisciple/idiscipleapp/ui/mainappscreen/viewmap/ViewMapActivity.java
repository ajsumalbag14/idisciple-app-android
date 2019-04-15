package com.ph.idisciple.idiscipleapp.ui.mainappscreen.viewmap;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jsibbold.zoomage.ZoomageView;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.JsonBuildingItemRaw;
import com.ph.idisciple.idiscipleapp.data.local.model.JsonBuildingRawList;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ViewMapActivity extends BaseActivity {

    @BindView(R.id.rvList) RecyclerView rvList;
    @BindView(R.id.ivZoomageView) ZoomageView ivZoomageView;

    @OnClick(R.id.ivShowList)
    public void onShowListClick(){
        isListShown = !isListShown;
        rvList.setVisibility(isListShown ? View.GONE : View.VISIBLE);
    }

    private LinearLayoutManager mLinearLayoutManager;
    private MapLegendsAdapter mAdapter;
    private boolean isListShown = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_view_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(ViewMapActivity.this);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        InputStream rawBuilding = getResources().openRawResource(R.raw.buildings);
        Reader rdBuilding = new BufferedReader(new InputStreamReader(rawBuilding));
        JsonBuildingRawList mJsonListBloodType = new Gson().fromJson(rdBuilding, JsonBuildingRawList.class);
        ArrayList<JsonBuildingItemRaw> mRawData = mJsonListBloodType.getList();

        mAdapter = new MapLegendsAdapter(ViewMapActivity.this, mRawData);
        rvList.setAdapter(mAdapter);
    }
}

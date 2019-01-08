package com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;
import com.ph.idisciple.idiscipleapp.data.local.repository.Speaker.SpeakerRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import java.util.List;

import butterknife.BindView;

public class SpeakerFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;

    private MainAppScreenActivity mActivity;
    private GridLayoutManager mGridLayoutManager;
    private SpeakerAdapter mAdapter;
    private SpeakerRepository mSpeakerRepository;
    private List<Speaker> mSpeakerList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_speakers, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mSpeakerRepository = new SpeakerRepository();
        mSpeakerList = mSpeakerRepository.getContentList();

        mGridLayoutManager = new GridLayoutManager(mActivity, 2);
        rvList.setLayoutManager(mGridLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        mAdapter = new SpeakerAdapter(mActivity, mSpeakerList);
        rvList.setAdapter(mAdapter);

        mAdapter.setClickListener(new SpeakerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Speaker selectedSpeaker = mSpeakerList.get(position);
                Bundle bundleToInclude = new Bundle();
                bundleToInclude.putString("name", selectedSpeaker.getSpeakerName());
                bundleToInclude.putString("topic", selectedSpeaker.getSpeakerPlanaryTitle());
                bundleToInclude.putString("bio", selectedSpeaker.getSpeakerBio());
                bundleToInclude.putString("avatar", selectedSpeaker.getSpeakerImageUrl());
                mActivity.redirectToAnotherScreen(SpeakerInfoDialogActivity.class, bundleToInclude);
            }
        });

        return rootView;
    }
}

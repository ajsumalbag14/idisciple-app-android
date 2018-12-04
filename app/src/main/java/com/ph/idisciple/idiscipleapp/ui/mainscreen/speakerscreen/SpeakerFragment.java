package com.ph.idisciple.idiscipleapp.ui.mainscreen.speakerscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.remote.model.Speaker;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SpeakerFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;

    private GridLayoutManager mGridLayoutManager;
    private SpeakerAdapter mSpeakerAdapter;
    private List<Speaker> mSpeakerList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_speakers, container, false);
        bind(rootView);

        mSpeakerList = new ArrayList<>();
        mSpeakerList.add(new Speaker("Joe Doe", "Why the Gospel is still relevant?", "12-02-12 13:00:00"));
        mSpeakerList.add(new Speaker("Jane Doe", "Why the Gospel is still relevant?", "12-02-12 13:00:00"));

        mSpeakerAdapter = new SpeakerAdapter(getActivity(), mSpeakerList);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvList.setLayoutManager(mGridLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setAdapter(mSpeakerAdapter);

        return rootView;

    }


}

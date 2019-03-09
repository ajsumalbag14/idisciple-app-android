package com.ph.idisciple.idiscipleapp.ui.mainappscreen.schedulefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import java.util.List;

import butterknife.BindView;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class ScheduleListFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;
    private LinearLayoutManager mLinearLayoutManager;
    private ScheduleListAdapter mAdapter;
    private List<Schedule> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recyclerview, container, false);
        bind(rootView);

        Boolean isToday = getArguments().getBoolean("isToday", false);
        String selectedDate = getArguments().getString("selectedDate", "");
        mData = ((MainAppScreenActivity) getActivity()).mPresenter.mScheduleRepository.getContentList();
        List<Schedule> selectedScheduleList = from(mData).where("getScheduleDate", eq(selectedDate)).all();

        List<Profile> AttendeesList = ((MainAppScreenActivity) getActivity()).mPresenter.mAttendeesRepository.getContentList();
        Profile currentProfile = from(AttendeesList).where("getId", eq(((MainAppScreenActivity) getActivity()).mUserId)).first();

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        mAdapter = new ScheduleListAdapter(getContext(), selectedScheduleList, isToday, currentProfile);
        rvList.setAdapter(mAdapter);

        return rootView;
    }
}

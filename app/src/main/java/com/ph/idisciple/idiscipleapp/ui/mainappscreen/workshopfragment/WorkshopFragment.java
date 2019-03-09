package com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment;

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
import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import java.util.List;

import butterknife.BindView;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class WorkshopFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;

    private MainAppScreenActivity mActivity;
    private LinearLayoutManager mLinearLayoutManager;
    private WorkshopAdapter mAdapter;
    private List<Workshop> mWorkshopList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recyclerview, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mWorkshopList = mActivity.mPresenter.mWorkshopRepository.getContentList();

        List<Profile> AttendeesList = ((MainAppScreenActivity) getActivity()).mPresenter.mAttendeesRepository.getContentList();
        Profile currentProfile = from(AttendeesList).where("getId", eq(((MainAppScreenActivity) getActivity()).mUserId)).first();

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        mAdapter = new WorkshopAdapter(mActivity, mWorkshopList, currentProfile);
        rvList.setAdapter(mAdapter);

        return rootView;
    }
}

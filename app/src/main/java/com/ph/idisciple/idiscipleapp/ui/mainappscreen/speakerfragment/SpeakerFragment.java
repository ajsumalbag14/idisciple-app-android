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
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.EnableDisableSwipeRefreshLayout;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class SpeakerFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;

    private MainAppScreenActivity mActivity;
    private GridLayoutManager mGridLayoutManager;
    private SpeakerAdapter mAdapter;
    private List<Speaker> mSpeakerList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recyclerview, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();

        mGridLayoutManager = new GridLayoutManager(mActivity, 2);
        rvList.setLayoutManager(mGridLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                EventBus.getDefault().post(new EnableDisableSwipeRefreshLayout(mGridLayoutManager.findFirstCompletelyVisibleItemPosition() == 0));
            }
        });

        onUpdateSpeakerList(null);

        mAdapter.setClickListener(new SpeakerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Speaker selectedSpeaker = mSpeakerList.get(position);
                Bundle bundleToInclude = new Bundle();
                bundleToInclude.putString("name", selectedSpeaker.getSpeakerName());
                bundleToInclude.putString("topic", selectedSpeaker.getSpeakerPlanaryTitle());
                bundleToInclude.putString("bio", selectedSpeaker.getSpeakerBio());
                bundleToInclude.putString("avatar", selectedSpeaker.getSpeakerImageUrl());
                bundleToInclude.putString("countryId", selectedSpeaker.getSpeakerNationality());
                mActivity.redirectToAnotherScreen(SpeakerInfoDialogActivity.class, bundleToInclude);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateSpeakerList(RefreshSpeakerListEvent event){
        mActivity = (MainAppScreenActivity) getActivity();
        mSpeakerList = mActivity.mPresenter.mSpeakerRepository.getContentList();
        mAdapter = new SpeakerAdapter(mActivity, mSpeakerList);
        rvList.setAdapter(mAdapter);
    }
}

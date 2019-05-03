package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

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
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import java.util.List;

import butterknife.BindView;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class ContactListPerGroupFragment extends BaseFragment{

    @BindView(R.id.rvList) RecyclerView rvList;

    private MainAppScreenActivity mActivity;
    private AttendeesAdapter mAdapter;
    private List<Profile> mAllContactList = null;
    private List<Profile> mFilteredContactList = null;
    private String selectedFamilyGroupId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recyclerview, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mAllContactList = mActivity.mPresenter.mAttendeesRepository.getContentList();

        Bundle bundle = getArguments();
        if(bundle != null)
            selectedFamilyGroupId = getArguments().getString("familyGroupId");

        if(!selectedFamilyGroupId.equals(""))
            mFilteredContactList = from(mAllContactList).where("getUserFamilyGroupId", eq(selectedFamilyGroupId)).all();

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mActivity);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        mAdapter = new AttendeesAdapter(mActivity, mFilteredContactList == null ? mAllContactList : mFilteredContactList, selectedFamilyGroupId);
        rvList.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // [5/3/2019] Comment Favorite for now
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        // [5/3/2019] Comment Favorite for now
//        EventBus.getDefault().unregister(this);
    }

    // [5/3/2019] Comment Favorite for now
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onUpdateFavorite(RefreshFavoriteEvent event){
//        mActivity = (MainAppScreenActivity) getActivity();
//        mAdapter = new AttendeesAdapter(mActivity, mFilteredContactList == null ? mAllContactList : mFilteredContactList, selectedFamilyGroupId);
//        rvList.setAdapter(mAdapter);
//    }

}

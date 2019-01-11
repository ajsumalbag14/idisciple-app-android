package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

public class CommunityTabDelegatesFragment extends BaseFragment {

    private MainAppScreenActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_community, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();

        return rootView;
    }
}

package com.ph.idisciple.idiscipleapp.ui.mainscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainscreen.speakerscreen.SpeakerFragment;

import butterknife.OnClick;

public class DashboardFragment extends BaseFragment {

    @OnClick(R.id.clDashboardButtonSchedule)
    public void onScheduleClick(){
        Fragment redirectToFragment = Fragment.instantiate(getActivity(), SpeakerFragment.class.getName(), null);
        mActivity.replaceFragment(redirectToFragment, false);
    }

    private MainScreenActivity mActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dashboard, container, false);
        bind(rootView);

        mActivity = (MainScreenActivity) getActivity();
        return rootView;

    }
}

package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;

import butterknife.BindString;
import butterknife.BindView;

public class MoreTabAboutFragment extends BaseFragment {

    @BindView(R.id.tvAboutUsDescription) TextView tvAboutUsDescription;
    @BindString(R.string.more_tab_about_description) String moreAboutUsDescription;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_more_tab_about_us, container, false);
        bind(rootView);
        tvAboutUsDescription.setText(moreAboutUsDescription);
        return rootView;
    }
}

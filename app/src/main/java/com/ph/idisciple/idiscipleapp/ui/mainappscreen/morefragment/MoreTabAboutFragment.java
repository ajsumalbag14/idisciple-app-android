package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.AboutContent;
import com.ph.idisciple.idiscipleapp.data.local.repository.About.AboutContentRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MoreTabAboutFragment extends BaseFragment {

    @BindView(R.id.tvAboutTitleConference) TextView tvAboutTitleConference;
    @BindView(R.id.tvAboutContentConference) TextView tvAboutContentConference;
    @BindView(R.id.tvAboutTitleUs) TextView tvAboutTitleUs;
    @BindView(R.id.tvAboutContentUs) TextView tvAboutContentUs;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_more_tab_about_us, container, false);
        bind(rootView);
        AboutContentRepository mAboutContentRepository = new AboutContentRepository();
        ArrayList<AboutContent> aboutContents = mAboutContentRepository.getContentList();
        onFetchAboutUsSuccess(aboutContents);
        return rootView;
    }

    private void onFetchAboutUsSuccess(ArrayList<AboutContent> aboutContentContentList) {
        tvAboutTitleConference.setText(aboutContentContentList.get(0).getAboutTitle());
        tvAboutContentConference.setText(aboutContentContentList.get(0).getAboutContent());

        if(aboutContentContentList.size() > 1) {
            tvAboutTitleUs.setText(aboutContentContentList.get(1).getAboutTitle());
            tvAboutContentUs.setText(aboutContentContentList.get(1).getAboutContent());
        }
    }
}

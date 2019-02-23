package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_more_tab_about_us, container, false);
        bind(rootView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvAboutUsDescription.setText(Html.fromHtml(moreAboutUsDescription, Html.FROM_HTML_MODE_LEGACY));
        } else
            tvAboutUsDescription.setText(Html.fromHtml(moreAboutUsDescription).toString());

        return rootView;
    }
}

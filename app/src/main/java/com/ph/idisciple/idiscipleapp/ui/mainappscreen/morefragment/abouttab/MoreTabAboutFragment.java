package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.abouttab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import java.util.List;

import butterknife.BindView;

public class MoreTabAboutFragment extends BaseFragment implements MoreTabAboutContract.View {

    @BindView(R.id.tvAboutTitleConference) TextView tvAboutTitleConference;
    @BindView(R.id.tvAboutContentConference) TextView tvAboutContentConference;
    @BindView(R.id.tvAboutTitleUs) TextView tvAboutTitleUs;
    @BindView(R.id.tvAboutContentUs) TextView tvAboutContentUs;

    private MainAppScreenActivity mActivity;
    private MoreTabAboutContract.Presenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_more_tab_about_us, container, false);
        bind(rootView);
        mActivity = (MainAppScreenActivity) getActivity();
        mPresenter = new MoreTabAboutPresenter(this);
        mPresenter.fetchAboutUs();
        return rootView;
    }

    @Override
    public void onFetchAboutUsFailed(String errorMessage) {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), errorMessage);
    }

    @Override
    public void onFetchAboutUsSuccess(List<AboutContent> aboutContentContentList) {
        tvAboutTitleConference.setText(aboutContentContentList.get(0).getAboutTitle());
        tvAboutContentConference.setText(aboutContentContentList.get(0).getAboutContent());

        tvAboutTitleUs.setText(aboutContentContentList.get(1).getAboutTitle());
        tvAboutContentUs.setText(aboutContentContentList.get(1).getAboutContent());
    }

    @Override
    public void showNoInternetConnection() {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_no_internet), getString(R.string.dialog_error_message_no_internet));
        mActivity.hideLoadingDialog();
    }

    @Override
    public void showTimeoutError() {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_timeout), getString(R.string.dialog_error_message_no_internet));
        mActivity.hideLoadingDialog();
    }

    @Override
    public void showGenericError() {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
        mActivity.hideLoadingDialog();
    }
}

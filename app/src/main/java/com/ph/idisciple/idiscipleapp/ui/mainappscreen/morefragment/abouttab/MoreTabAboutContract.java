package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.abouttab;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

import java.util.List;

public class MoreTabAboutContract {

    interface View extends BaseView<Presenter> {
        void onFetchAboutUsFailed(String errorMessage);
        void onFetchAboutUsSuccess(List<AboutContent> aboutContentContentList);
    }

    interface Presenter extends BasePresenter {
        void fetchAboutUs();
    }
}

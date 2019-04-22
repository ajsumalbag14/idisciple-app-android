package com.ph.idisciple.idiscipleapp.ui.mainappscreen;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

public class MainAppScreenContract {

    interface View extends BaseView<Presenter> {
        void onFetchDataFailed(String errorMessage);
        void onFetchDataSuccess();
        void setUserId(String userId);
    }

    interface Presenter extends BasePresenter {
        void fetchData();
    }
}

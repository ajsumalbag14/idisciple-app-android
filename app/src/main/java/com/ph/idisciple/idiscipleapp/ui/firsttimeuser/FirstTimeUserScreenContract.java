package com.ph.idisciple.idiscipleapp.ui.firsttimeuser;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

public class FirstTimeUserScreenContract {
    interface View extends BaseView<Presenter> {
        void onUpdatePasswordFailed();
        void onUpdatePasswordSuccess(boolean isFirstTimeUser, String token);
    }

    interface Presenter extends BasePresenter {
        void updatePassword(String emailAddress, String password);
    }
}

package com.ph.idisciple.idiscipleapp.ui.login;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

public class LoginScreenContract {
    interface View extends BaseView<Presenter> {
        void onLoginFailed();
        void onLoginSuccess(boolean isFirstTimeUser);
    }

    interface Presenter extends BasePresenter {
        void validateLogin(String emailAddress, String password);
    }
}

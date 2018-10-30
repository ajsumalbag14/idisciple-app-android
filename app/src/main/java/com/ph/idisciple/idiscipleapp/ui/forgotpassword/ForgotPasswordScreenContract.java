package com.ph.idisciple.idiscipleapp.ui.forgotpassword;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

public class ForgotPasswordScreenContract {
    interface View extends BaseView<Presenter> {
        void onResetPasswordFailed();
        void onResetPasswordSuccess(boolean isFirstTimeUser, String token);
    }

    interface Presenter extends BasePresenter {
        void resetPassword(String emailAddress);
    }
}

package com.ph.idisciple.idiscipleapp.ui.forgotpassword;

import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

public class ForgotPasswordScreenPresenter implements ForgotPasswordScreenContract.Presenter {

    private UserService mUserService;
    private ForgotPasswordScreenContract.View mView;
    private ForgotPasswordScreenActivity mActivity;

    public ForgotPasswordScreenPresenter(ForgotPasswordScreenActivity activity, ForgotPasswordScreenContract.View view) {
        mView = view;
        mActivity = activity;
        mUserService = RestClient.getInstance().getUserService();
    }

    @Override
    public void resetPassword(String emailAddress) {

    }
}

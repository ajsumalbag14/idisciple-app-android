package com.ph.idisciple.idiscipleapp.ui.forgotpassword;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.OnClick;

public class ForgotPasswordScreenActivity extends BaseActivity implements ForgotPasswordScreenContract.View {

    @OnClick(R.id.tvBackToLogin)
    public void onBackToLoginActivity(){
        onBackPressed();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResetPasswordFailed() {

    }

    @Override
    public void onResetPasswordSuccess(boolean isFirstTimeUser, String token) {

    }

    @Override
    public void showNoInternetConnection() {

    }

    @Override
    public void showTimeoutError() {

    }

    @Override
    public void showGenericError() {

    }
}

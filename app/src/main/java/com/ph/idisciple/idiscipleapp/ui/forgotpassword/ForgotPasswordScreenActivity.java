package com.ph.idisciple.idiscipleapp.ui.forgotpassword;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordScreenActivity extends BaseActivity implements ForgotPasswordScreenContract.View {

    @BindString(R.string.button_link_back_to_login) String linkBackToLogin;
    @BindView(R.id.etEmailAddress) EditText etEmailAddress;
    @BindView(R.id.tvBackToLogin) TextView tvBackToLogin;
    @BindView(R.id.bEmailResetLink) Button bEmailResetLink;

    @BindView(R.id.tvError) TextView tvError;
    @BindView(R.id.llConfirmation) LinearLayout llConfirmation;
    @BindView(R.id.llError) LinearLayout llError;

    @OnClick(R.id.tvBackToLogin)
    public void onBackToLoginActivity(){
        onBackPressed();
    }

    private ForgotPasswordScreenContract.Presenter mPresenter;
    @Override
    protected int getLayout() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ForgotPasswordScreenPresenter(ForgotPasswordScreenActivity.this, this);
        updateButtonIfEnabled(checkIfAllRequiredFieldsAreNotEmpty());

        SpannableString content = new SpannableString(linkBackToLogin);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvBackToLogin.setText(content);

        etEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateButtonIfEnabled(checkIfAllRequiredFieldsAreNotEmpty());
                llError.setVisibility(View.INVISIBLE);
            }
        });
    }

    private boolean checkIfAllRequiredFieldsAreNotEmpty() {
        return !TextUtils.isEmpty(etEmailAddress.getText().toString());
    }

    private void updateButtonIfEnabled(boolean isEnabled){
        hideLoadingDialog();
        bEmailResetLink.setEnabled(isEnabled);
        bEmailResetLink.setTextColor(Color.WHITE);
        bEmailResetLink.setBackgroundResource(isEnabled ? R.drawable.shape_button_green : R.drawable.shape_button_gray);
    }

    @Override
    public void onResetPasswordFailed() {

    }

    @Override
    public void onResetPasswordSuccess(boolean isFirstTimeUser, String token) {
        llConfirmation.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoInternetConnection() {
        updateButtonIfEnabled(true);
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_no_internet), getString(R.string.dialog_error_message_no_internet));
    }

    @Override
    public void showTimeoutError() {
        updateButtonIfEnabled(true);
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_timeout), getString(R.string.dialog_error_message_no_internet));
    }

    @Override
    public void showGenericError() {
        updateButtonIfEnabled(true);
        getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
    }
}

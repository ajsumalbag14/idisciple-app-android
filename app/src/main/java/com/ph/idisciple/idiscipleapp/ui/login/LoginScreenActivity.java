package com.ph.idisciple.idiscipleapp.ui.login;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.firsttimeuser.FirstTimeUserScreenActivity;
import com.ph.idisciple.idiscipleapp.ui.forgotpassword.ForgotPasswordScreenActivity;
import com.ph.idisciple.idiscipleapp.ui.mainscreen.MainScreenActivity;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginScreenActivity extends BaseActivity implements LoginScreenContract.View{

    @BindDrawable(R.drawable.ic_password_eye_visible) Drawable drawablePasswordEyeVisible;
    @BindDrawable(R.drawable.ic_password_eye) Drawable drawablePasswordEyeHidden;
    @BindString(R.string.button_link_forgot_password) String linkForgotPassword;

    @BindView(R.id.etEmailAddress) EditText etEmailAddress;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.llError) LinearLayout llError;
    @BindView(R.id.bLogin) Button bLogin;
    @BindView(R.id.tvForgotPassword) TextView tvForgotPassword;
    @BindView(R.id.tvError) TextView tvError;
    @BindView(R.id.ivPasswordEye) ImageView ivPasswordEye;

    @OnClick(R.id.ivPasswordEye)
    public void onPasswordEyeClick(){
        isPasswordVisible = !isPasswordVisible;
        ivPasswordEye.setImageDrawable(isPasswordVisible ? drawablePasswordEyeHidden : drawablePasswordEyeVisible);
        etPassword.setInputType(isPasswordVisible
                ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setSelection(etPassword.getText().length());
    }

    @OnClick(R.id.bLogin)
    public void onLoginClick(){

        String emailAddress = etEmailAddress.getText().toString();
        String password = etPassword.getText().toString();

        if(isValid(emailAddress, password)) {
            updateButtonIfEnabled(false);
            showLoadingDialog();
            mPresenter.validateLogin(emailAddress, password);
        }
    }

    @OnClick(R.id.tvForgotPassword)
    public void onForgotPasswordClick(){
        redirectToAnotherScreen(ForgotPasswordScreenActivity.class);
    }

    private boolean isPasswordVisible = false;
    private LoginScreenContract.Presenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LoginScreenPresenter(LoginScreenActivity.this, this);

        SpannableString content = new SpannableString(linkForgotPassword);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvForgotPassword.setText(content);

        updateButtonIfEnabled(checkIfAllRequiredFieldsAreNotEmpty());

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


        etPassword.addTextChangedListener(new TextWatcher() {
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
        return !TextUtils.isEmpty(etEmailAddress.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString());
    }

    private boolean isValid(String emailAddress, String password){

        boolean isValid = true;

        if(!TextUtils.isEmpty(emailAddress)){
            if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                isValid = false;
                llError.setVisibility(View.VISIBLE);
            }
        } else if(TextUtils.isEmpty(emailAddress)) {
            isValid = false;
            llError.setVisibility(View.VISIBLE);
        } else if(TextUtils.isEmpty(password)) {
            isValid = false;
            llError.setVisibility(View.VISIBLE);
        }
        return isValid;
    }

    private void updateButtonIfEnabled(boolean isEnabled){
        hideLoadingDialog();
        bLogin.setEnabled(isEnabled);
        bLogin.setTextColor(Color.WHITE);
        bLogin.setBackgroundResource(isEnabled ? R.drawable.shape_button_green : R.drawable.shape_button_gray);
    }

    @Override
    public void onLoginFailed(String errorMessage) {
        updateButtonIfEnabled(true);
        tvError.setText(errorMessage);
        llError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess(boolean isFirstTimeUser, String token, String userId) {
        updateButtonIfEnabled(true);
        if(isFirstTimeUser) {
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            bundle.putString("userId", userId);
            redirectToAnotherScreen(FirstTimeUserScreenActivity.class, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            redirectToAnotherScreenAsFirstScreen(MainScreenActivity.class, bundle);
        }
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

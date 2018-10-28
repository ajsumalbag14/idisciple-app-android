package com.ph.idisciple.idiscipleapp.ui.login;

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
import com.ph.idisciple.idiscipleapp.ui.forgotpassword.ForgotPasswordScreenActivity;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginScreenActivity extends BaseActivity implements LoginScreenContract.View{

    @BindDrawable(R.drawable.ic_password_eye_visible) Drawable drawablePasswordEyeVisible;
    @BindDrawable(R.drawable.ic_password_eye) Drawable drawablePasswordEyeHidden;

    @BindView(R.id.etEmailAddress) EditText etEmailAddress;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.llError) LinearLayout llError;
    @BindView(R.id.bLogin) Button bLogin;
    @BindView(R.id.tvForgotPassword) TextView tvForgotPassword;
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

        SpannableString content = new SpannableString("Content");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvForgotPassword.setText(content);


        etEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                llError.setVisibility(View.GONE);
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
                llError.setVisibility(View.GONE);
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
        //.hideLoadingDialog();
        bLogin.setEnabled(isEnabled);
        //bLogin.setTextColor(isEnabled ? Color.WHITE : mActivity.getResources().getColor(R.color.colorButtonDisabled));
    }

    @Override
    public void onLoginFailed() {
        llError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess(boolean isFirstTimeUser) {
        updateButtonIfEnabled(true);
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

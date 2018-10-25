package com.ph.idisciple.idiscipleapp.ui.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.forgotpassword.ForgotPasswordScreenActivity;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginScreenActivity extends BaseActivity{

    @BindDrawable(R.drawable.ic_password_eye_visible) Drawable drawablePasswordEyeVisible;
    @BindDrawable(R.drawable.ic_password_eye) Drawable drawablePasswordEyeHidden;

    @BindView(R.id.etEmailAddress) EditText etEmailAddress;
    @BindView(R.id.etPassword) EditText etPassword;
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

    }

    @OnClick(R.id.tvForgotPassword)
    public void onForgotPasswordClick(){
        redirectToAnotherScreen(ForgotPasswordScreenActivity.class);
    }

    private boolean isPasswordVisible = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        etEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean isEnabled = checkIfAllRequiredFieldsAreNotEmpty();
                updateButtonIfEnabled(isEnabled);
                //showError(false, "");
            }
        });
    }


    private boolean checkIfAllRequiredFieldsAreNotEmpty() {
        return !TextUtils.isEmpty(etEmailAddress.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString());
    }

    private void updateButtonIfEnabled(boolean isEnabled){
        //.hideLoadingDialog();
        bLogin.setEnabled(isEnabled);
        //bLogin.setTextColor(isEnabled ? Color.WHITE : mActivity.getResources().getColor(R.color.colorButtonDisabled));
    }

}

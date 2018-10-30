package com.ph.idisciple.idiscipleapp.ui.firsttimeuser;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

public class FirstTimeUserScreenActivity extends BaseActivity implements FirstTimeUserScreenContract.View{

    @BindString(R.string.error_passwords_do_not_match) String errorPasswordsDoNotMatch;
    @BindString(R.string.error_required_field_password) String errorRequiredPassword;

    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.etConfirmPassword) EditText etConfirmPassword;
    @BindView(R.id.bChangePassword) Button bChangePassword;
    @BindView(R.id.llError) LinearLayout llError;
    @BindView(R.id.tvError) TextView tvError;

    @OnClick(R.id.bChangePassword)
    public void onChangePasswordClick(){
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if(isValid(password, confirmPassword)) {
            updateButtonIfEnabled(false);
        }
    }

    private FirstTimeUserScreenContract.Presenter mPresenter;
    private String mToken;

    @Override
    protected int getLayout() {
        return R.layout.activity_first_time_password;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToken = getIntent().getExtras().getString("token");
        mPresenter = new FirstTimeUserScreenPresenter(FirstTimeUserScreenActivity.this, this);

        updateButtonIfEnabled(checkIfAllRequiredFieldsAreNotEmpty());

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                llError.setVisibility(View.INVISIBLE);
                updateButtonIfEnabled(checkIfAllRequiredFieldsAreNotEmpty());
            }
        });

        etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                llError.setVisibility(View.INVISIBLE);
                updateButtonIfEnabled(checkIfAllRequiredFieldsAreNotEmpty());
            }
        });
    }

    private boolean checkIfAllRequiredFieldsAreNotEmpty() {
        return !TextUtils.isEmpty(etPassword.getText().toString()) && !TextUtils.isEmpty(etConfirmPassword.getText().toString());
    }

    private boolean isValid(String password, String confirmPassword){

        boolean isValid = true;

        if(TextUtils.isEmpty(password)) {
            isValid = false;
            tvError.setText(errorRequiredPassword);
            llError.setVisibility(View.VISIBLE);
        } else if(TextUtils.isEmpty(confirmPassword)) {
            isValid = false;
            tvError.setText(errorRequiredPassword);
            llError.setVisibility(View.VISIBLE);
        } else if(password.equals(confirmPassword)) {
            isValid = false;
            tvError.setText(errorPasswordsDoNotMatch);
            llError.setVisibility(View.VISIBLE);
        }
        return isValid;
    }

    private void updateButtonIfEnabled(boolean isEnabled){
        hideLoadingDialog();
        bChangePassword.setEnabled(isEnabled);
        bChangePassword.setTextColor(Color.WHITE);
        bChangePassword.setBackgroundResource(isEnabled ? R.drawable.shape_button_green : R.drawable.shape_button_gray);
    }

    @Override
    public void onUpdatePasswordFailed() {
        updateButtonIfEnabled(true);
        // TODO: onUpdatePasswordFailed
    }

    @Override
    public void onUpdatePasswordSuccess(boolean isFirstTimeUser, String token) {
        updateButtonIfEnabled(true);
        // TODO: onUpdatePasswordSuccess
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

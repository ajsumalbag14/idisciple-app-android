package com.ph.idisciple.idiscipleapp.ui.forgotpassword;

import com.google.gson.Gson;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mUserService.resetPassword(emailAddress).enqueue(new Callback<BaseApi>() {
            @Override
            public void onResponse(Call<BaseApi> call, Response<BaseApi> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        mView.onResetPasswordSuccess();
                        break;

                    case 422:
                        Gson gson = new Gson();
                        try {

                            BaseApi apiErrorResponse = gson.fromJson(response.errorBody().string(), BaseApi.class);
                            mView.onResetPasswordFailed(apiErrorResponse.getMessage());

                        } catch (IOException e) {
                            e.printStackTrace();
                            mView.showGenericError();
                        }
                        break;

                    default:
                        mView.showGenericError();
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseApi> call, Throwable t) {
                if (t.getCause() != null && t.getCause().getMessage().contains("ENETUNREACH (Network is unreachable)"))
                    mView.showNoInternetConnection();
                else if ((t.getCause() != null && t.getCause().getMessage().contains("ETIMEDOUT (Connection timed out)")) || t.getMessage().contains("failed to connect"))
                    mView.showTimeoutError();
                else
                    mView.showGenericError();
            }
        });
    }
}

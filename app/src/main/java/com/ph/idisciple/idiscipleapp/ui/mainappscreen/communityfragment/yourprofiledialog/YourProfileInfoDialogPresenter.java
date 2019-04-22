package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog;

import com.google.gson.Gson;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourProfileInfoDialogPresenter implements YourProfileInfoDialogContract.Presenter {

    private YourProfileInfoDialogContract.View mView;
    private UserService mUserService;

    public YourProfileInfoDialogPresenter(YourProfileInfoDialogContract.View view){
        mView = view;
        mUserService = RestClient.getInstance().getUserService();
    }

    @Override
    public void onLogout(String userId) {
        mUserService.logoutUser(userId).enqueue(new Callback<BaseApi>(){

            @Override
            public void onResponse(Call<BaseApi> call, Response<BaseApi> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        mView.onLogoutSuccess();
                        break;
                    case 422:
                        Gson gson = new Gson();
                        try {

                            BaseApi apiErrorResponse = gson.fromJson(response.errorBody().string(), BaseApi.class);
                            mView.onLogoutFailed(apiErrorResponse.getMessage());

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

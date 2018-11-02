package com.ph.idisciple.idiscipleapp.ui.firsttimeuser;

import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.Wrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.UserAccount;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstTimeUserScreenPresenter implements FirstTimeUserScreenContract.Presenter {


    private UserService mUserService;
    private FirstTimeUserScreenContract.View mView;
    private FirstTimeUserScreenActivity mActivity;

    private KeySettingsRepository mKeySettingsRepository;

    public FirstTimeUserScreenPresenter(FirstTimeUserScreenActivity activity, FirstTimeUserScreenContract.View view) {
        mView = view;
        mActivity = activity;
        mUserService = RestClient.getInstance().getSetupService();
        mKeySettingsRepository = new KeySettingsRepository();
    }

    @Override
    public void updatePassword(String newPassword) {
        mUserService.firstPasswordUpdate(mActivity.mToken, newPassword).enqueue(new Callback<Wrapper<UserAccount>>() {
            @Override
            public void onResponse(Call<Wrapper<UserAccount>> call, Response<Wrapper<UserAccount>> response) {
                switch (response.code()) {
                    case 200:
                        final String mToken = response.body().getData().getToken();
                        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.TOKEN, mToken, new IKeySettingsRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.IS_LOGGED_IN, "true", new IKeySettingsRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {
                                mView.onUpdatePasswordSuccess(mToken);
                            }
                        });
                        break;
                    case 422:
                    default:
                        mView.showGenericError();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Wrapper<UserAccount>> call, Throwable t) {
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

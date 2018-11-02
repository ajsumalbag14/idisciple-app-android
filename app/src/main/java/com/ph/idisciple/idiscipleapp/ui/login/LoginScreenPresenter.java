package com.ph.idisciple.idiscipleapp.ui.login;

import com.google.gson.Gson;
import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.model.ProfileObject;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.IProfileRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.ProfileRepository;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.Profile;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.ListWrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.request.LoginRequest;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.LoginResponse;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.UserAccount;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenPresenter implements LoginScreenContract.Presenter {

    private UserService mUserService;
    private LoginScreenContract.View mView;
    private LoginScreenActivity mActivity;

    private ProfileRepository mProfileRepository;
    private KeySettingsRepository mKeySettingsRepository;

    public LoginScreenPresenter(LoginScreenActivity activity, LoginScreenContract.View view) {
        mView = view;
        mActivity = activity;
        mUserService = RestClient.getInstance().getSetupService();
        mProfileRepository = new ProfileRepository();
        mKeySettingsRepository = new KeySettingsRepository();
    }

    @Override
    public void validateLogin(String emailAddress, String password) {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(emailAddress);
        loginRequest.setPassword(password);

        mUserService.loginUser(loginRequest.mParams).enqueue(new Callback<ListWrapper<LoginResponse>>() {
            @Override
            public void onResponse(Call<ListWrapper<LoginResponse>> call, Response<ListWrapper<LoginResponse>> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        final UserAccount mUserAccess = response.body().getData().get(0).getUserAccess();
                        Profile mProfile = response.body().getData().get(0).getProfile();

                        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.TOKEN, mUserAccess.getToken(), new IKeySettingsRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });

                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.USER_ID, mUserAccess.getUserId(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.USERNAME, mUserAccess.getUserName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });


                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.FIRST_NAME, mProfile.getFirstName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.LAST_NAME, mProfile.getLastName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.MIDDLENAME, mProfile.getMiddleName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.NICKNAME, mProfile.getNickName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });


                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.BIRTHDATE, mProfile.getBirthdate(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.MOBILE_NUMBER, mProfile.getMobileNumber(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.GENDER, mProfile.getGender(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.COUNTRY, mProfile.getCountry(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.IS_PWD, String.valueOf(mProfile.getIsPwdTag()), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });

                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.CREATED_AT_DATE, mProfile.getCreatedAt(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.UPDATED_AT_DATE, mProfile.getUpdatedAt(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {
                                mView.onLoginSuccess(mUserAccess.isFirstTimeUser(), mUserAccess.getToken(), mUserAccess.getUserId());
                            }
                        });
                        break;

                    case 422:
                        Gson gson = new Gson();
                        try {

                            BaseApi apiErrorResponse = gson.fromJson(response.errorBody().string(), BaseApi.class);
                            mView.onLoginFailed(apiErrorResponse.getMessage());

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
            public void onFailure(Call<ListWrapper<LoginResponse>> call, Throwable t) {
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

package com.ph.idisciple.idiscipleapp.ui.login;

import com.google.gson.Gson;
import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.ProfileObject;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.IProfileRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.ProfileRepository;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.EventDetails;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.ListWrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.request.LoginRequest;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.LoginResponse;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.LoginUserAccount;
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
        mUserService = RestClient.getInstance().getUserService();
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
                        final LoginUserAccount mUserAccess = response.body().getData().get(0).getUserAccess();
                        Profile mProfile = response.body().getData().get(0).getProfile();
                        EventDetails mEventDetails = response.body().getData().get(0).getEventDetails();

                        if(!mUserAccess.isFirstTimeUser())
                            mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.IS_LOGGED_IN, "true", new IKeySettingsRepository.onSaveCallback() {
                                @Override
                                public void onSuccess() {

                                }
                            });

                        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.TOKEN, mUserAccess.getToken(), new IKeySettingsRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });

                        // Profile
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

                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.FULL_NAME, mProfile.getUserFullName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.AVATAR, mProfile.getUserImageUrl(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });

                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.FIRST_NAME, mProfile.getUserFirstName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.LAST_NAME, mProfile.getUserLastName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.MIDDLENAME, mProfile.getUserMiddleName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.NICKNAME, mProfile.getUserNickName(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });

                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.GENDER, mProfile.getUserGender(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.COUNTRY, mProfile.getUserCountry(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });

                        // Event Details
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.ROOM_NUMBER, mEventDetails.getRooomNumber(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.WORKSHOP_ID1, mEventDetails.getWorkshopId1(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.WORKSHOP_ID2, mEventDetails.getWorkshopId2(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.FAMILY_GROUP_ID, mEventDetails.getFamilyGroupId(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.TSHIRT_SIZE, mEventDetails.getTshirtSize(), new IProfileRepository.onSaveCallback() {
                            @Override
                            public void onSuccess() {

                            }
                        });
                        mProfileRepository.saveKeyItem(ProfileObject.ProfileType.IS_CITY_TOUR, mEventDetails.getTagCityTour(), new IProfileRepository.onSaveCallback() {
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

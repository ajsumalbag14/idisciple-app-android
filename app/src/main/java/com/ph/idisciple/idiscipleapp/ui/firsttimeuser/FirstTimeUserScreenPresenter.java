package com.ph.idisciple.idiscipleapp.ui.firsttimeuser;

import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

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
    public void updatePassword(String emailAddress, String password) {
        //mUserService.firstPasswordUpdate()

        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.IS_LOGGED_IN, "true", new IKeySettingsRepository.onSaveCallback() {
            @Override
            public void onSuccess() {

            }
        });
    }
}

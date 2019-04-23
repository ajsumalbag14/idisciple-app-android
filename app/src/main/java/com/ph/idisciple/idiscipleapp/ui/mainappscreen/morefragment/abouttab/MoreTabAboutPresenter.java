package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.abouttab;

import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.service.UserService;

public class MoreTabAboutPresenter implements MoreTabAboutContract.Presenter{

    private MoreTabAboutContract.View mView;
    private UserService mUserService;

    public MoreTabAboutPresenter(MoreTabAboutContract.View view){
        mView = view;
        mUserService = RestClient.getInstance().getUserService();
    }

    @Override
    public void fetchAboutUs() {

    }
}

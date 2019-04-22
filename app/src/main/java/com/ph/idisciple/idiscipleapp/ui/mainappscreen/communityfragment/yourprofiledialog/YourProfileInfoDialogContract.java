package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

public class YourProfileInfoDialogContract {

    interface View extends BaseView<Presenter>
    {
        void onLogoutFailed(String errorMessage);
        void onLogoutSuccess();
    }

    interface Presenter extends BasePresenter {
        void onLogout(String userId);
    }
}

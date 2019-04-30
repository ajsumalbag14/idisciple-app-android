package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog;

import android.graphics.Bitmap;

import com.ph.idisciple.idiscipleapp.ui.BasePresenter;
import com.ph.idisciple.idiscipleapp.ui.BaseView;

public class YourProfileInfoDialogContract {

    interface View extends BaseView<Presenter>
    {
        void onLogoutFailed(String errorMessage);
        void onLogoutSuccess();

        void onUploadPhotoSuccess(String imageUrl);
        void onUploadPhotoFailed(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void onLogout(String userId);
        void onUploadPhoto(Bitmap bitmap, String fileName, String userId);
    }
}

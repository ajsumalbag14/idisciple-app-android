package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.yourprofiledialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.IProfileRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.ProfileRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.login.LoginScreenActivity;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.RefreshAvatarEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.internal.IOException;

public class YourProfileDialogFragment extends DialogFragment implements YourProfileInfoDialogContract.View {

    private final int REQUEST_CODE_SHOW_GALLERY = 0x1;
    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvDelegateNickName) TextView tvDelegateNickName;
    @BindView(R.id.tvFullName) TextView tvFullName;
    @BindView(R.id.tvCountryDetails) TextView tvCountryDetails;
    @BindView(R.id.tvFamilyGroupAssignedTo) TextView tvFamilyGroupAssignedTo;
    @BindView(R.id.tvAttendingWorkshop) TextView tvAttendingWorkshop;
    @BindView(R.id.cvUploadAvatar) CardView cvUploadAvatar;
    @BindView(R.id.llChangeAvatar) LinearLayout llChangeAvatar;

    private YourProfileInfoDialogContract.Presenter mPresenter;
    private KeySettingsRepository mKeySettingsRepository;
    private ProfileRepository mProfileRepository;
    private String mUserId;
    private String mCountryId;
    private AlertDialog mLogoutDialog;
    private BaseActivity mActivity;

    public YourProfileDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static YourProfileDialogFragment newInstance(Bundle bundleToInclude) {
        YourProfileDialogFragment frag = new YourProfileDialogFragment();
        frag.setArguments(bundleToInclude);
        return frag;
    }

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked() {
        dismiss();
    }

    @OnClick(R.id.tvLogout)
    public void onLogoutClicked() {
        mLogoutDialog = mActivity.getShowMessageUtil().showConfirmMessage("Log-out", getString(R.string.dialog_confirm_logout), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onLogout(mUserId);
                mLogoutDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.tvFamilyGroupAssignedTo)
    public void onFamilyGroupAssignedToClick() {

    }

    @OnClick(R.id.cvUploadAvatar)
    public void onUploadAvatarClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_SHOW_GALLERY);
    }

    @OnClick(R.id.llChangeAvatar)
    public void onChangeAvatarClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_SHOW_GALLERY);
    }
    // [5/3/2019] Comment Favorite for now
//    private boolean isFavorite = false;
//    private SavedProfileFavoritesRepository mSavedProfileFavoritesRepository;

    @OnClick(R.id.ivAvatar)
    public void onAvatarClicked() {
        onChangeAvatarClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_own_profile_info, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActivity = (BaseActivity) getActivity();
        mPresenter = new YourProfileInfoDialogPresenter(this);
        mKeySettingsRepository = new KeySettingsRepository();
        mProfileRepository = new ProfileRepository();

        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserId = bundle.getString("id", "");
            tvDelegateNickName.setText(bundle.getString("nickname"));
            tvFullName.setText(bundle.getString("fullname"));
            tvCountryDetails.setText(bundle.getString("countryName"));
            tvFamilyGroupAssignedTo.setText(bundle.getString("familyGroupName", "none"));

            mCountryId = bundle.getString("countryId");

            Glide.with(mActivity)
                    .load(bundle.getString("avatar"))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            showAvatarImageOptions(false);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            showAvatarImageOptions(true);
                            ivAvatar.setImageDrawable(resource);
                            return true;
                        }
                    })
                    .apply(RequestOptions
                            .centerCropTransform()
                            .placeholder(mActivity.getDrawableCountryRes(mCountryId))
                            .error(mActivity.getDrawableCountryRes(mCountryId)))
                    .into(ivAvatar);


            String workshopName1 = bundle.getString("workshopId1Name");
            String workshopName2 = bundle.getString("workshopId2Name");
            String workshopCombined = TextUtils.isEmpty(workshopName1) ? "none" : workshopName1 + (TextUtils.isEmpty(workshopName2) ? "" : " & " + workshopName2);
            tvAttendingWorkshop.setText(workshopCombined);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SHOW_GALLERY:
                if (resultCode == mActivity.RESULT_OK) {
                    Uri uri = data.getData();

                    try {
                        String fileName = "img_" + mUserId + ".png";

                        Bitmap bitmapPhotoSelected = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), uri);
                        mPresenter.onUploadPhoto(getScaledBitmap(bitmapPhotoSelected), fileName, mUserId);
                        mActivity.showLoadingDialog();

                    } catch (IOException | java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * Description: This method scales the Bitmap image to the desired image size set
     *
     * @param bitmap [Bitmap]
     */
    private Bitmap getScaledBitmap(Bitmap bitmap) {

        double y = 480;
        double x = 480;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) x, (int) y, true);
        bitmap.recycle();
        bitmap = scaledBitmap;

        return bitmap;
    }

    private void showAvatarImageOptions(boolean isImageLoadedSuccessful) {
        cvUploadAvatar.setVisibility(isImageLoadedSuccessful ? View.GONE : View.VISIBLE);
        llChangeAvatar.setVisibility(isImageLoadedSuccessful ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLogoutFailed(String errorMessage) {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), errorMessage);
    }

    @Override
    public void onLogoutSuccess() {
        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.IS_LOGGED_IN, "false", new IKeySettingsRepository.onSaveCallback() {
            @Override
            public void onSuccess() {

            }
        });

        mKeySettingsRepository.saveKeyItem(KeySettings.ItemType.TOKEN, "", new IKeySettingsRepository.onSaveCallback() {
            @Override
            public void onSuccess() {

            }
        });

        mProfileRepository.resetStorage(new IProfileRepository.onSaveCallback() {
            @Override
            public void onSuccess() {
                // Go back to LoginScreen
                mActivity.redirectToAnotherScreenAsFirstScreen(LoginScreenActivity.class);
            }
        });
    }

    @Override
    public void onUploadPhotoSuccess(Bitmap bitmap, String imageUrl) {

        EventBus.getDefault().post(new RefreshAvatarEvent(imageUrl));

        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCornerRadius(0f);
        Glide.with(mActivity)
                .load(roundedBitmapDrawable)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        showAvatarImageOptions(false);
                        mActivity.hideLoadingDialog();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        showAvatarImageOptions(true);
                        ivAvatar.setImageDrawable(resource);
                        mActivity.hideLoadingDialog();
                        return true;
                    }
                })
                .apply(RequestOptions
                        .centerCropTransform()
                        .placeholder(mActivity.getDrawableCountryRes(mCountryId))
                        .error(mActivity.getDrawableCountryRes(mCountryId)))
                .into(ivAvatar);

        showAvatarImageOptions(true);
    }

    @Override
    public void onUploadPhotoFailed(String errorMessage) {

    }

    @Override
    public void showNoInternetConnection() {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_no_internet), getString(R.string.dialog_error_message_no_internet));
        mActivity.hideLoadingDialog();
    }

    @Override
    public void showTimeoutError() {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_timeout), getString(R.string.dialog_error_message_no_internet));
        mActivity.hideLoadingDialog();
    }

    @Override
    public void showGenericError() {
        mActivity.getShowMessageUtil().showOkMessage(getString(R.string.dialog_error_title_generic), getString(R.string.dialog_error_message_generic));
        mActivity.hideLoadingDialog();
    }
}

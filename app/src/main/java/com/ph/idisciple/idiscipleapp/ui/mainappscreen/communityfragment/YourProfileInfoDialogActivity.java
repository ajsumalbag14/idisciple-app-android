package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.KeySettings;
import com.ph.idisciple.idiscipleapp.data.local.repository.IKeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.IProfileRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.KeySettingsRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.ProfileRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;
import com.ph.idisciple.idiscipleapp.ui.login.LoginScreenActivity;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.internal.IOException;

public class YourProfileInfoDialogActivity extends BaseActivity {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvDelegateNickName) TextView tvDelegateNickName;
    @BindView(R.id.tvFullName) TextView tvFullName;
    @BindView(R.id.tvCountryDetails) TextView tvCountryDetails;
    @BindView(R.id.tvFamilyGroupAssignedTo) TextView tvFamilyGroupAssignedTo;
    @BindView(R.id.tvAttendingWorkshop) TextView tvAttendingWorkshop;
    @BindView(R.id.cvUploadAvatar) CardView cvUploadAvatar;
    @BindView(R.id.llChangeAvatar) LinearLayout llChangeAvatar;
    private KeySettingsRepository mKeySettingsRepository;
    private ProfileRepository mProfileRepository;
    private final int REQUEST_CODE_SHOW_GALLERY = 0x1;

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked() {
        finish();
    }

    @OnClick(R.id.tvLogout)
    public void onLogoutClicked() {
        getShowMessageUtil().showConfirmMessage("Log-out", getString(R.string.dialog_confirm_logout), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        redirectToAnotherScreenAsFirstScreen(LoginScreenActivity.class);
                    }
                });
            }
        });
    }

    @OnClick(R.id.tvFamilyGroupAssignedTo)
    public void onFamilyGroupAssignedToClick(){

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

    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_own_profile_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);

        mKeySettingsRepository = new KeySettingsRepository();
        mProfileRepository = new ProfileRepository();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvDelegateNickName.setText(bundle.getString("nickname"));
            tvFullName.setText(bundle.getString("fullname"));
            tvCountryDetails.setText(bundle.getString("countryName"));
            tvFamilyGroupAssignedTo.setText(bundle.getString("familyGroupName"));

            Glide.with(YourProfileInfoDialogActivity.this)
                    .load(bundle.getString("avatar"))
                    .apply(RequestOptions
                            .circleCropTransform()
                            .error(getDrawableCountryRes(bundle.getString("countryId"))))
                    .into(ivAvatar);

            String workshopName1 = bundle.getString("workshopId1Name");
            String workshopName2 = bundle.getString("workshopId2Name");
            String workshopCombined = TextUtils.isEmpty(workshopName1) ? "none" : workshopName1 + (TextUtils.isEmpty(workshopName2) ? "" : " & " + workshopName2) ;
            tvAttendingWorkshop.setText( workshopCombined );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SHOW_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    try {
                        Bitmap bitmapPhotoSelected = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapPhotoSelected);
                        roundedBitmapDrawable.setCornerRadius(10f);
                        ivAvatar.setImageDrawable(roundedBitmapDrawable);
                        //toggleBetweenCaptureAndSelected(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}

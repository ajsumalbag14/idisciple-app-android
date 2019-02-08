package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
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

import butterknife.BindView;
import butterknife.OnClick;

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

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked() {
        finish();
    }

    @OnClick(R.id.tvLogout)
    public void onLogoutClicked() {
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

    @OnClick(R.id.tvFamilyGroupAssignedTo)
    public void onFamilyGroupAssignedToClick(){

    }

    @OnClick(R.id.cvUploadAvatar)
    public void onUploadAvatarClicked() {

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
}

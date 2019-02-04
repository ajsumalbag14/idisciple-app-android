package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

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

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked(){
        finish();
    }

    @OnClick(R.id.cvUploadAvatar)
    public void onUploadAvatarClicked(){

    }

    @OnClick(R.id.llChangeAvatar)
    public void onChangeAvatarClicked(){

    }


    @Override
    protected int getLayout() {
        return R.layout.dialog_own_profile_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            tvDelegateNickName.setText(bundle.getString("nickname"));
            tvFullName.setText(bundle.getString("fullname"));
            tvCountryDetails.setText(bundle.getString("country"));
            tvFamilyGroupAssignedTo.setText(bundle.getString("familyGroupName"));
            tvAttendingWorkshop.setText(bundle.getString("workshop1"));
            Glide.with(YourProfileInfoDialogActivity.this).load(bundle.getString("avatar")).into(ivAvatar);
        }
    }
}

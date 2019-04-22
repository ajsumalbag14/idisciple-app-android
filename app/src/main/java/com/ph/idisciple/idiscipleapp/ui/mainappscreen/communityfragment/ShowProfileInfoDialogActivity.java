package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.repository.SavedProfileFavorites.ISavedProfileFavoritesRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.SavedProfileFavorites.SavedProfileFavoritesRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ShowProfileInfoDialogActivity extends BaseActivity {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.ivFavorite) ImageView ivFavorite;
    @BindView(R.id.tvDelegateNickName) TextView tvDelegateNickName;
    @BindView(R.id.tvFamilyGroupLeaderTag) TextView tvFamilyGroupLeaderTag;
    @BindView(R.id.tvFullName) TextView tvFullName;
    @BindView(R.id.tvCountryDetails) TextView tvCountryDetails;
    @BindView(R.id.tvFamilyGroupAssignedTo) TextView tvFamilyGroupAssignedTo;
    @BindView(R.id.tvAttendingWorkshop) TextView tvAttendingWorkshop;

    @OnClick(R.id.tvDismiss)
    public void onDismissClick(){
        finish();
    }

    @OnClick(R.id.ivFavorite)
    public void onFavoriteToggleClick(){
        // Store in local DB
        final boolean isSelectedAsFavorite = !isFavorite;

        mSavedProfileFavoritesRepository.setAsFavorite(id, isSelectedAsFavorite, new ISavedProfileFavoritesRepository.onSaveCallback() {
            @Override
            public void onSuccess(boolean isFavoriteSet) {
                isFavorite = isSelectedAsFavorite;
                setImageForFavorites(isFavoriteSet, ivFavorite);
                EventBus.getDefault().post(new RefreshFavoriteEvent());
            }
        });
    }

    @OnClick(R.id.tvFamilyGroupAssignedTo)
    public void onFamilyGroupAssignedToClick(){

    }

    private String id;
    private boolean isFavorite = false;
    private SavedProfileFavoritesRepository mSavedProfileFavoritesRepository;

    @Override
    protected int getLayout() {
        return R.layout.dialog_show_profile_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);

        mSavedProfileFavoritesRepository = new SavedProfileFavoritesRepository();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            id = bundle.getString("id", "0");
            tvDelegateNickName.setText(bundle.getString("nickname"));
            tvFullName.setText(bundle.getString("fullname"));
            tvCountryDetails.setText(bundle.getString("countryName"));
            tvFamilyGroupAssignedTo.setText(bundle.getString("familyGroupName"));

            Glide.with(ShowProfileInfoDialogActivity.this)
                    .load(bundle.getString("avatar"))
                    .apply(RequestOptions
                            .circleCropTransform()
                            .placeholder(getDrawableCountryRes(bundle.getString("countryId")))
                            .error(getDrawableCountryRes(bundle.getString("countryId"))))
                    .into(ivAvatar);

            tvFamilyGroupLeaderTag.setVisibility(bundle.getBoolean("isFgTag", false) ? View.VISIBLE : View.GONE);
            isFavorite = bundle.getBoolean("isFavorite", false);
            setImageForFavorites(isFavorite, ivFavorite);

            String workshopName1 = bundle.getString("workshopId1Name");
            String workshopName2 = bundle.getString("workshopId2Name");
            String workshopCombined = TextUtils.isEmpty(workshopName1) ? "none" : workshopName1 + (TextUtils.isEmpty(workshopName2) ? "" : " & " + workshopName2) ;
            tvAttendingWorkshop.setText( workshopCombined );
        }
    }

    private void setImageForFavorites(boolean isSetAsFavorite, ImageView ivIsFavorite) {
        Drawable isFavoriteDrawable = getDrawable(isSetAsFavorite ? R.drawable.ic_star_active : R.drawable.ic_star);
        Glide.with(ShowProfileInfoDialogActivity.this).load(isFavoriteDrawable).into(ivIsFavorite);
    }
}

package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowProfileDialogFragment extends DialogFragment {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.ivFavorite) ImageView ivFavorite;
    @BindView(R.id.tvDelegateNickName) TextView tvDelegateNickName;
    @BindView(R.id.tvFamilyGroupLeaderTag) TextView tvFamilyGroupLeaderTag;
    @BindView(R.id.tvFullName) TextView tvFullName;
    @BindView(R.id.tvCountryDetails) TextView tvCountryDetails;
    @BindView(R.id.tvFamilyGroupAssignedTo) TextView tvFamilyGroupAssignedTo;
    @BindView(R.id.tvAttendingWorkshop) TextView tvAttendingWorkshop;
    private String id;

    public ShowProfileDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ShowProfileDialogFragment newInstance(Bundle bundleToInclude) {
        ShowProfileDialogFragment frag = new ShowProfileDialogFragment();
        frag.setArguments(bundleToInclude);
        return frag;
    }

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked() {
        dismiss();
    }

    @OnClick(R.id.tvFamilyGroupAssignedTo)
    public void onFamilyGroupAssignedToClick() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_show_profile_info, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // [5/3/2019] Comment Favorite for now
//        mSavedProfileFavoritesRepository = new SavedProfileFavoritesRepository();

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id", "0");
            tvDelegateNickName.setText(bundle.getString("nickname"));
            tvFullName.setText(bundle.getString("fullname"));
            tvCountryDetails.setText(bundle.getString("countryName"));
            tvFamilyGroupAssignedTo.setText(bundle.getString("familyGroupName", "none"));

            Glide.with(getActivity())
                    .load(bundle.getString("avatar"))
                    .apply(RequestOptions
                            .centerCropTransform()
                            .placeholder(((BaseActivity) getActivity()).getDrawableCountryRes(bundle.getString("countryId")))
                            .error(((BaseActivity) getActivity()).getDrawableCountryRes(bundle.getString("countryId"))))
                    .into(ivAvatar);

            tvFamilyGroupLeaderTag.setVisibility(bundle.getBoolean("isFgTag", false) ? View.VISIBLE : View.GONE);

            // [5/3/2019] Comment Favorite for now
//            isFavorite = bundle.getBoolean("isFavorite", false);
//            setImageForFavorites(isFavorite, ivFavorite);

            String workshopName1 = bundle.getString("workshopId1Name");
            String workshopName2 = bundle.getString("workshopId2Name");
            String workshopCombined = TextUtils.isEmpty(workshopName1) ? "none" : workshopName1 + (TextUtils.isEmpty(workshopName2) ? "" : " & " + workshopName2);
            tvAttendingWorkshop.setText(workshopCombined);
        }
    }


    // [5/3/2019] Comment Favorite for now
//    private boolean isFavorite = false;
//    private SavedProfileFavoritesRepository mSavedProfileFavoritesRepository;

    // [5/3/2019] Comment Favorite for now
//    @OnClick(R.id.ivFavorite)
//    public void onFavoriteToggleClick(){
//        // Store in local DB
//        final boolean isSelectedAsFavorite = !isFavorite;
//
//        mSavedProfileFavoritesRepository.setAsFavorite(id, isSelectedAsFavorite, new ISavedProfileFavoritesRepository.onSaveCallback() {
//            @Override
//            public void onSuccess(boolean isFavoriteSet) {
//                isFavorite = isSelectedAsFavorite;
//                setImageForFavorites(isFavoriteSet, ivFavorite);
//                EventBus.getDefault().post(new RefreshFavoriteEvent());
//            }
//        });
//    }

    // [5/3/2019] Comment Favorite for now
//    private void setImageForFavorites(boolean isSetAsFavorite, ImageView ivIsFavorite) {
//        Drawable isFavoriteDrawable = getDrawable(isSetAsFavorite ? R.drawable.ic_star_active : R.drawable.ic_star);
//        Glide.with(ShowProfileInfoDialogActivity.this).load(isFavoriteDrawable).into(ivIsFavorite);
//    }
}

package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Country;
import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.SavedProfileFavorites;
import com.ph.idisciple.idiscipleapp.data.local.repository.SavedProfileFavorites.ISavedProfileFavoritesRepository;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class AttendeesAdapter extends RecyclerView.Adapter<AttendeesAdapter.ViewHolder> {

    private List<Profile> mData;
    private List<FamilyGroup> mFamilyGroupList;
    private List<Country> mCountryList;
    private LayoutInflater mInflater;
    private Context mContext;
    private MainAppScreenActivity mActivity;

    // data is passed into the constructor
    public AttendeesAdapter(Context context, List<Profile> data) {
        mContext = context;
        mActivity = (MainAppScreenActivity) mContext;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mFamilyGroupList = mActivity.mPresenter.mFamilyGroupRepository.getContentList();
        mCountryList = mActivity.mPresenter.mCountryRepository.getContentList();
    }

    // data is passed into the constructor
    public AttendeesAdapter(Context context, List<Profile> data, String familyGroupId) {
        mContext = context;
        mActivity = (MainAppScreenActivity) mContext;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mFamilyGroupList = mActivity.mPresenter.mFamilyGroupRepository.getContentList();
        mFamilyGroupList = from(mFamilyGroupList).where("getId", eq(familyGroupId)).all();
        mCountryList = mActivity.mPresenter.mCountryRepository.getContentList();
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_name, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Profile item = getItem(position);
        holder.tvNickname.setText(item.getUserNickName());

        Glide.with(mContext)
                .load(item.getUserImageUrl())
                .apply(RequestOptions
                        .circleCropTransform()
                        .error(mActivity.getDrawableCountryRes(item.getUserCountry())))
                .into(holder.ivAvatar);

        // Determine if profile is yours
        if (mActivity.mUserId.equals(item.getId())) {
            holder.tvCompleteName.setVisibility(View.GONE);
            holder.tvThatsYou.setVisibility(View.VISIBLE);
        } else {
            holder.tvThatsYou.setVisibility(View.GONE);
            holder.tvCompleteName.setVisibility(View.VISIBLE);
            holder.tvCompleteName.setText(item.getUserFullName());
        }

        Log.d("Profile", "Name:" + item.getUserNickName());
        Log.d("Profile", "ID:" + item.getId());

        // Determine if profile is FG
        for (FamilyGroup familyGroup : mFamilyGroupList) {
            if (item.getId().equals(familyGroup.getFamilyGroupLeadId())) {
                holder.tvFamilyGroupLeaderTag.setVisibility(View.VISIBLE);
                break;
            } else
                holder.tvFamilyGroupLeaderTag.setVisibility(View.GONE);
        }

        final SavedProfileFavorites profileFavorite = mActivity.mPresenter.mSavedProfileFavoritesRepository.findItemById(item.getId());
        setImageForFavorites(profileFavorite != null ? profileFavorite.isTagAsFavorite() : false, holder.ivFavorite);
        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store in local DB
                boolean isSelectedAsFavorite = false;
                if (profileFavorite != null)
                    isSelectedAsFavorite = !profileFavorite.isTagAsFavorite();

                mActivity.mPresenter.mSavedProfileFavoritesRepository.setAsFavorite(item.getId(), isSelectedAsFavorite, new ISavedProfileFavoritesRepository.onSaveCallback() {
                    @Override
                    public void onSuccess(boolean isFavoriteSet) {
                        setImageForFavorites(isFavoriteSet, holder.ivFavorite);
                        EventBus.getDefault().post(new RefreshFavoriteEvent());
                    }
                });

            }
        });

        holder.clItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile selectedAttendee = mData.get(position);
                Bundle bundleToInclude = new Bundle();
                bundleToInclude.putString("fullname", selectedAttendee.getUserFullName());
                bundleToInclude.putString("nickname", selectedAttendee.getUserNickName());
                bundleToInclude.putString("country", selectedAttendee.getUserCountry());
                bundleToInclude.putString("familyGroupName", selectedAttendee.getUserFamilyGroupName());
                bundleToInclude.putString("workshop1", selectedAttendee.getUserWorkshop1());
                bundleToInclude.putString("avatar", selectedAttendee.getUserImageUrl());
                mActivity.redirectToAnotherScreen(YourProfileInfoDialogActivity.class, bundleToInclude);
            }
        });
    }

    private void setImageForFavorites(boolean isSetAsFavorite, ImageView ivIsFavorite) {
        Drawable isFavoriteDrawable = mActivity.getDrawable(isSetAsFavorite ? R.drawable.ic_star_active : R.drawable.ic_star);
        Glide.with(mActivity).load(isFavoriteDrawable).into(ivIsFavorite);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    Profile getItem(int id) {
        return mData.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.clItem) ConstraintLayout clItem;
        @BindView(R.id.tvNickname) TextView tvNickname;
        @BindView(R.id.tvFamilyGroupLeaderTag) TextView tvFamilyGroupLeaderTag;
        @BindView(R.id.tvCompleteName) TextView tvCompleteName;
        @BindView(R.id.tvThatsYou) TextView tvThatsYou;
        @BindView(R.id.ivFavorite) ImageView ivFavorite;
        @BindView(R.id.ivAvatar) ImageView ivAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {

    private List<Workshop> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private int selectedItemPosition = -1;
    private Profile currentProfile;

    // data is passed into the constructor
    public WorkshopAdapter(Context context, List<Workshop> data, Profile currentProfile) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.currentProfile = currentProfile;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_workshop, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Workshop itemWorkshop = getItem(position);
        holder.tvWorkshopName.setText(itemWorkshop.getWorkshopName());
        holder.tvWorkshopSpeakerName.setText(itemWorkshop.getWorkshopFacilitator());
        holder.tvWorkshopDateTimeLocation.setText(itemWorkshop.getWorkshopScheduleDate() + " " + itemWorkshop.getWorkshopScheduleTime() + " / " + itemWorkshop.getWorkshopLocation());

        holder.ivMenuOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
                selectedItemPosition = position;
            }
        });

        if(currentProfile.getUserWorkshop1().equals(itemWorkshop.getId()) || currentProfile.getUserWorkshop1().equals(itemWorkshop.getId())){
            holder.tvWorkshopYours.setVisibility(View.VISIBLE);
        } else
            holder.tvWorkshopYours.setVisibility(View.GONE);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(mContext, v);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_workshop_item_options);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Workshop selectedWorkshop = getItem(selectedItemPosition);
        Bundle bundleToInclude = new Bundle();

        switch (menuItem.getItemId()) {
            case R.id.menu_workshop_option_view_desc:
                bundleToInclude.putString("name", selectedWorkshop.getWorkshopName());
                bundleToInclude.putString("type", "Workshop Blurb");
                bundleToInclude.putString("details", selectedWorkshop.getWorkshopDescription());
                ((BaseActivity) mContext).redirectToAnotherScreen(WorkshopInfoDialogActivity.class, bundleToInclude);
                return true;
            case R.id.menu_workshop_option_view_outline:
                bundleToInclude.putString("name", selectedWorkshop.getWorkshopName());
                bundleToInclude.putString("type", "Workshop Outline");
                bundleToInclude.putString("details", selectedWorkshop.getWorkshopOutline());
                ((BaseActivity) mContext).redirectToAnotherScreen(WorkshopInfoDialogActivity.class, bundleToInclude);
                return true;
            default:
                return false;
        }
    }

    // convenience method for getting data at click position
    Workshop getItem(int id) {
        return mData.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvWorkshopYours) TextView tvWorkshopYours;
        @BindView(R.id.tvWorkshopName) TextView tvWorkshopName;
        @BindView(R.id.tvWorkshopSpeakerName) TextView tvWorkshopSpeakerName;
        @BindView(R.id.tvWorkshopDateTimeLocation) TextView tvWorkshopDateTimeLocation;
        @BindView(R.id.ivMenuOptions) ImageView ivMenuOptions;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

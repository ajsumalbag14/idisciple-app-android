package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.resourcestab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.ph.idisciple.idiscipleapp.data.local.model.Resource;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {

    private List<Resource> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private int selectedItemPosition = -1;

    // data is passed into the constructor
    public ResourceAdapter(Context context, List<Resource> data) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_resource, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Resource itemResource = getItem(position);
        holder.tvResourceName.setText(itemResource.getResourceTitle());
        holder.tvResourceDescription.setText(itemResource.getResourceDescription());
        holder.tvResourceTypeSize.setText(itemResource.getResourceType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resource itemResource = getItem(position);
                Bundle bundleToInclude = new Bundle();
                bundleToInclude.putString("name", itemResource.getResourceTitle());
                bundleToInclude.putString("type", itemResource.getResourceType());
                bundleToInclude.putString("details", itemResource.getResourceDescription());
                bundleToInclude.putString("url", itemResource.getResourceUrl());
                ((BaseActivity) mContext).redirectToAnotherScreen(ViewResourceActivity.class, bundleToInclude);
            }
        });

        holder.ivMenuOptions.setVisibility((itemResource.getResourceType().equals("video")) ? View.GONE : View.VISIBLE);
        holder.ivMenuOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
                selectedItemPosition = position;
            }
        });
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
        popup.inflate(R.menu.menu_resource_item_options);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Resource selectedResource = getItem(selectedItemPosition);
        Bundle bundleToInclude = new Bundle();

        switch (menuItem.getItemId()) {
            case R.id.menu_resources_option_download_file:
                Intent browserDownloadIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedResource.getResourceUrl()));
                mContext.startActivity(browserDownloadIntent);
                return true;
            default:
                return false;
        }
    }

    // convenience method for getting data at click position
    Resource getItem(int id) {
        return mData.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvResourceName) TextView tvResourceName;
        @BindView(R.id.tvResourceDescription) TextView tvResourceDescription;
        @BindView(R.id.tvResourceTypeSize) TextView tvResourceTypeSize;
        @BindView(R.id.ivMenuOptions) ImageView ivMenuOptions;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

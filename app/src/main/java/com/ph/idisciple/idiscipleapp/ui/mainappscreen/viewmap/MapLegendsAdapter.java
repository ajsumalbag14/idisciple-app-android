package com.ph.idisciple.idiscipleapp.ui.mainappscreen.viewmap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.JsonBuildingItemRaw;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapLegendsAdapter extends RecyclerView.Adapter<MapLegendsAdapter.ViewHolder> {

    private ArrayList<JsonBuildingItemRaw> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public MapLegendsAdapter(Context context, ArrayList<JsonBuildingItemRaw> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_map_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final JsonBuildingItemRaw item = mData.get(position);
        holder.tvBuildingName.setText(item.getItemName());
        holder.tvBuildingDescription.setText(item.getItemDescription());
        holder.tvLegendLetter.setText(item.getItemLetter());

        holder.tvBuildingDescription.setVisibility((TextUtils.isEmpty(item.getItemDescription())) ? View.GONE : View.VISIBLE);
        holder.tvLegendLetter.setTextSize(TypedValue.COMPLEX_UNIT_SP, item.getItemLetter().length() == 2 ? 12 : 16);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvBuildingName) TextView tvBuildingName;
        @BindView(R.id.tvBuildingDescription) TextView tvBuildingDescription;
        @BindView(R.id.tvLegendLetter) TextView tvLegendLetter;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

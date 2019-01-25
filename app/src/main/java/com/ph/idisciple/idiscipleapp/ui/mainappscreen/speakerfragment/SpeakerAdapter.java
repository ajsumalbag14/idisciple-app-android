package com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.ViewHolder> {

    private List<Speaker> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    public SpeakerAdapter(Context context, List<Speaker> data) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_speaker, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Speaker itemSpeaker = getItem(position);
        holder.tvSpeakerName.setText(itemSpeaker.getSpeakerName());
        holder.tvSpeakerTopic.setText(itemSpeaker.getSpeakerPlanaryTitle());
        holder.tvSpeakerDateTime.setText(itemSpeaker.getSpeakerPlanaryScheduleDate() + " " + itemSpeaker.getSpeakerPlanaryScheduleTime());
        Glide.with(mContext)
                .load(itemSpeaker.getSpeakerImageUrl())
                .apply( RequestOptions
                        .circleCropTransform()
                        .error(R.drawable.img_placeholder))
                .into(holder.ivSpeakerAvatar);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvSpeakerName) TextView tvSpeakerName;
        @BindView(R.id.tvSpeakerTopic) TextView tvSpeakerTopic;
        @BindView(R.id.tvSpeakerDateTime) TextView tvSpeakerDateTime;
        @BindView(R.id.ivSpeakerAvatar) ImageView ivSpeakerAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Speaker getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

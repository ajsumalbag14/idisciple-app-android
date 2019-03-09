package com.ph.idisciple.idiscipleapp.ui.mainappscreen.schedulefragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>  {

    private List<Schedule> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private boolean isToday;
    private Profile currentProfile;

    public ScheduleListAdapter(Context context, List<Schedule> data, boolean isToday, Profile currentProfile){
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.isToday = isToday;
        this.currentProfile = currentProfile;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mInflater.inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule itemSchedule = getItem(position);
        String scheduleTime = itemSchedule.getScheduleStartTime() + " - " + itemSchedule.getScheduleEndTime();
        holder.tvScheduleTime.setText(scheduleTime);
        holder.tvEventName.setText(itemSchedule.getScheduleName());
        holder.tvEventVenue.setText(itemSchedule.getScheduleVenue());

        if(!itemSchedule.getWorkshopId().equals("0") && (currentProfile.getUserWorkshop1().equals(itemSchedule.getWorkshopId()) || currentProfile.getUserWorkshop1().equals(itemSchedule.getWorkshopId()))){
            holder.tvWorkshopYours.setVisibility(View.VISIBLE);
        } else
            holder.tvWorkshopYours.setVisibility(View.GONE);


        // Check if it's happening today
        if(isToday) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mma");
                Calendar calendarParsedStartDate = Calendar.getInstance();
                Calendar calendarParsedEndDate = Calendar.getInstance();

                calendarParsedStartDate.setTime(formatter.parse(itemSchedule.getScheduleStartTime()));
                calendarParsedEndDate.setTime(formatter.parse(itemSchedule.getScheduleEndTime()));

                Calendar calendarDateNow = Calendar.getInstance();
                int nowHour = calendarDateNow.get(Calendar.HOUR_OF_DAY);
                int nowMinute = calendarDateNow.get(Calendar.MINUTE);
                int startHour = calendarParsedStartDate.get(Calendar.HOUR_OF_DAY);
                int endHour = calendarParsedEndDate.get(Calendar.HOUR_OF_DAY);

                // Check if middle of duration
                if (nowHour >= startHour && nowHour <= endHour) {
                    // Same Hour
                    if (nowHour == startHour) {
                        holder.tvEventHappening.setVisibility((nowMinute >= calendarParsedStartDate.get(Calendar.MINUTE))
                                ? View.VISIBLE
                                : View.GONE);
                    } else if (nowHour == endHour) {
                        holder.tvEventHappening.setVisibility((nowMinute <= calendarParsedEndDate.get(Calendar.MINUTE))
                                ? View.VISIBLE
                                : View.GONE);
                    } else
                        holder.tvEventHappening.setVisibility(View.VISIBLE);
                } else
                    holder.tvEventHappening.setVisibility(View.GONE);


            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else
            holder.tvEventHappening.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    Schedule getItem(int id) {
        return mData.get(id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvScheduleTime) TextView tvScheduleTime;
        @BindView(R.id.tvEventName) TextView tvEventName;
        @BindView(R.id.tvEventVenue) TextView tvEventVenue;
        @BindView(R.id.tvEventHappening) TextView tvEventHappening;
        @BindView(R.id.tvWorkshopYours) TextView tvWorkshopYours;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

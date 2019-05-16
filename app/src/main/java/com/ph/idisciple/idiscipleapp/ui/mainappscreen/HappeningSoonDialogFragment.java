package com.ph.idisciple.idiscipleapp.ui.mainappscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HappeningSoonDialogFragment extends DialogFragment {

    @BindView(R.id.tvEventName) TextView tvEventName;
    @BindView(R.id.tvEventStartTime) TextView tvEventStartTime;
    @BindView(R.id.tvEventVenue) TextView tvEventVenue;

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked(){
        dismiss();
    }

    public HappeningSoonDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static HappeningSoonDialogFragment newInstance(Schedule schedule) {
        HappeningSoonDialogFragment frag = new HappeningSoonDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", schedule.getScheduleName());
        args.putString("startTime", schedule.getScheduleStartTime());
        args.putString("venue", schedule.getScheduleVenue());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_happening_soon, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            String eventName = bundle.getString("name");
            tvEventName.setText(eventName);
            tvEventStartTime.setText("Starts at " + bundle.getString("startTime"));
            tvEventVenue.setText(eventName.equals("Break") ? "" : bundle.getString("venue"));
        }
    }
}

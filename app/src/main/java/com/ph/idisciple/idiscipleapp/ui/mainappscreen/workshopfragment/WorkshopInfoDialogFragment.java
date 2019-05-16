package com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkshopInfoDialogFragment extends DialogFragment {

    @BindView(R.id.tvWorkshopName) TextView tvWorkshopName;
    @BindView(R.id.tvWorkshopType) TextView tvWorkshopType;
    @BindView(R.id.tvWorkshopDetails) TextView tvWorkshopDetails;

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked(){
        dismiss();
    }

    public WorkshopInfoDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static WorkshopInfoDialogFragment newInstance(Workshop workshop, String workshopType) {
        WorkshopInfoDialogFragment frag = new WorkshopInfoDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", workshop.getWorkshopName());
        args.putString("type", workshopType);
        args.putString("details", workshop.getWorkshopDescription());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_workshop_info, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            tvWorkshopName.setText(bundle.getString("name"));
            tvWorkshopType.setText(bundle.getString("type"));
            tvWorkshopDetails.setText(bundle.getString("details"));
        }
//        getDialog().setTitle(title);
    }
}

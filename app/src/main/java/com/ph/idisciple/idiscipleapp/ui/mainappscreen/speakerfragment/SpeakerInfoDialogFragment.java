package com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpeakerInfoDialogFragment extends DialogFragment {

    @BindView(R.id.ivSpeakerAvatar) ImageView ivSpeakerAvatar;
    @BindView(R.id.tvSpeakerName) TextView tvSpeakerName;
    @BindView(R.id.tvSpeakerTopic) TextView tvSpeakerTopic;
    @BindView(R.id.tvSpeakerBio) TextView tvSpeakerBio;

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked(){
        dismiss();
    }

    public SpeakerInfoDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static SpeakerInfoDialogFragment newInstance(Speaker selectedSpeaker) {
        SpeakerInfoDialogFragment frag = new SpeakerInfoDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", selectedSpeaker.getSpeakerName());
        args.putString("topic", selectedSpeaker.getSpeakerPlanaryTitle());
        args.putString("bio", selectedSpeaker.getSpeakerBio());
        args.putString("avatar", selectedSpeaker.getSpeakerImageUrl());
        args.putString("countryId", selectedSpeaker.getSpeakerNationality());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_speaker_info, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            tvSpeakerName.setText(bundle.getString("name"));
            tvSpeakerTopic.setText(bundle.getString("topic"));
            tvSpeakerBio.setText(bundle.getString("bio"));

            Glide.with(getActivity())
                    .load(bundle.getString("avatar"))
                    .apply(RequestOptions
                            .circleCropTransform()
                            .placeholder(((BaseActivity) getActivity()).getDrawableCountryRes(bundle.getString("countryId")))
                            .error(((BaseActivity) getActivity()).getDrawableCountryRes(bundle.getString("countryId"))))
                    .into(ivSpeakerAvatar);
        }
    }
}

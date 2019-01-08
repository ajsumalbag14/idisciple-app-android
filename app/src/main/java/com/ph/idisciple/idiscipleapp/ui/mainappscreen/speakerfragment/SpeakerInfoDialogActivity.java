package com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SpeakerInfoDialogActivity extends BaseActivity {

    @BindView(R.id.ivSpeakerAvatar) ImageView ivSpeakerAvatar;
    @BindView(R.id.tvSpeakerName) TextView tvSpeakerName;
    @BindView(R.id.tvSpeakerTopic) TextView tvSpeakerTopic;
    @BindView(R.id.tvSpeakerBio) TextView tvSpeakerBio;

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked(){
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_speaker_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            tvSpeakerName.setText(bundle.getString("name"));
            tvSpeakerTopic.setText(bundle.getString("topic"));
            tvSpeakerBio.setText(bundle.getString("bio"));
            Glide.with(SpeakerInfoDialogActivity.this).load(bundle.getString("avatar")).into(ivSpeakerAvatar);
        }
    }
}

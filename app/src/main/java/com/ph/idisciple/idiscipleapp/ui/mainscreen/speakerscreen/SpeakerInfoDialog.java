package com.ph.idisciple.idiscipleapp.ui.mainscreen.speakerscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

public class SpeakerInfoDialog extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.dialog_speaker_info;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);
    }
}

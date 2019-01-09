package com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkshopInfoDialogActivity extends BaseActivity {

    @BindView(R.id.tvWorkshopName) TextView tvWorkshopName;
    @BindView(R.id.tvWorkshopType) TextView tvWorkshopType;
    @BindView(R.id.tvWorkshopDetails) TextView tvWorkshopDetails;

    @OnClick(R.id.tvDismiss)
    public void onDismissClicked(){
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_workshop_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            tvWorkshopName.setText(bundle.getString("name"));
            tvWorkshopType.setText(bundle.getString("type"));
            tvWorkshopDetails.setText(bundle.getString("details"));
        }
    }
}

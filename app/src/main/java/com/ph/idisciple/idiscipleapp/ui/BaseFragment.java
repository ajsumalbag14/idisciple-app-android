package com.ph.idisciple.idiscipleapp.ui;

import android.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected View rootView;
    private Unbinder mUnBinder;

    protected String TAG = className();

    public void bind(View view) {
        mUnBinder = ButterKnife.bind(this, view);
    }

    private String className() {
        return getClass().getSimpleName();
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

}

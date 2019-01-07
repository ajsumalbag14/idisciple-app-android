package com.ph.idisciple.idiscipleapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    /**
     * Create new Fragment instance
     * @param clazz
     * @param bundle
     * @param <T>
     * @return
     */
    public static <T extends BaseFragment> T newInstance(Class<T> clazz, Bundle bundle) {
        try {
            T newInstance = clazz.newInstance();
            newInstance.setArguments(bundle);
            return newInstance;
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T extends BaseFragment> T newInstance(Class<T> clazz) {
        return newInstance(clazz, null);
    }
}

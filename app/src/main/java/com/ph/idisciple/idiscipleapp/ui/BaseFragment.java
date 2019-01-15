package com.ph.idisciple.idiscipleapp.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

    /**
     * Shows a fragment from back stack if it exists.
     * Otherwise, create new instance from the specified fragment.
     *
     * @param fragmentManager Fragment Manager
     * @param containerViewId Layout resource id for Fragment
     * @param fragment        Fragment you want to show in the <code>containerViewId</> layout
     * @param addToBackStack  boolean if you want to add the fragment to back stack
     * @param bundleToInclude Bundle to pass to the next Fragment
     */
    public void showFragment(FragmentManager fragmentManager,
                             @IdRes int containerViewId,
                             Fragment fragment,
                             boolean addToBackStack,
                             Bundle bundleToInclude) {
        if (fragment != null) {

            String backStackName = fragment.getClass().getSimpleName();
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(backStackName);
            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (fragmentByTag == null)
                fragmentByTag = fragment;
            else
                ft.remove(fragmentByTag);

            if (bundleToInclude != null)
                fragmentByTag.setArguments(bundleToInclude);

            try {
                ft.replace(containerViewId, fragmentByTag, backStackName);
                if (addToBackStack) ft.addToBackStack(backStackName);
                ft.commit();
            } catch (Exception ex) {

            }

            fragmentManager.executePendingTransactions();
        }
    }

}

package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import butterknife.BindView;

public class MoreFragment extends BaseFragment {

    @BindView(R.id.tabStripMore) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewPagerMore) ViewPager viewpager;

    private BaseFragment fragmentActive = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_more, container, false);
        bind(rootView);

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getActivity(), getChildFragmentManager());
        viewpager.setAdapter(adapter);
        tabStrip.setViewPager(viewpager);

        return rootView;
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public BaseFragment getItem(int position) {
            if (position == 0) {
                return newInstance(MoreTabResourcesFragment.class);
            } else {
                return newInstance(MoreTabAboutFragment.class);
            }
        }

        // This determines the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            switch (position) {
                case 0:
                    return mContext.getString(R.string.tab_more_resources);
                case 1:
                    return mContext.getString(R.string.tab_more_about);
                default:
                    return null;
            }
        }

    }
}

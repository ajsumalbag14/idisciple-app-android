package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.content.Context;
import android.os.Bundle;
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

public class CommunityFragment extends BaseFragment {

    @BindView(R.id.tabStripCommunity) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewPagerCommunity) ViewPager viewpager;

    private MainAppScreenActivity mActivity;
    private SimpleFragmentPagerAdapter adapter;
    private BaseFragment fragmentActive = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_community, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();

        adapter = new SimpleFragmentPagerAdapter(mActivity, getChildFragmentManager());
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
                return newInstance(CommunityTabGroupsFragment.class);
            } else {
                return newInstance(CommunityTabDelegatesFragment.class);
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
                    return mContext.getString(R.string.tab_community_groups);
                case 1:
                    return mContext.getString(R.string.tab_community_delegates);
                default:
                    return null;
            }
        }

    }
}

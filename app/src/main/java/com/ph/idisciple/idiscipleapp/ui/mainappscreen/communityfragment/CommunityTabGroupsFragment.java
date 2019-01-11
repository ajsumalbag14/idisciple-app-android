package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.schedulefragment.ScheduleListFragment;

import java.util.List;

import butterknife.BindView;

public class CommunityTabGroupsFragment extends BaseFragment {

    @BindView(R.id.tabStripGroups) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewpagerGroups) ViewPager viewpager;

    private MainAppScreenActivity mActivity;
    private List<FamilyGroup> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_community_tab_groups, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mData = mActivity.mPresenter.mFamilyGroupRepository.getContentList();

//        viewpager.setAdapter(new CommunityGroupAdapter(getChildFragmentManager()));
//        tabStrip.setViewPager(viewpager);


        return rootView;
    }

    public class CommunityGroupAdapter extends FragmentPagerAdapter {

        public CommunityGroupAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mData.get(position).getFamilyGroupName();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Fragment getItem(int position) {
            return newInstance(ScheduleListFragment.class);
        }
    }
}

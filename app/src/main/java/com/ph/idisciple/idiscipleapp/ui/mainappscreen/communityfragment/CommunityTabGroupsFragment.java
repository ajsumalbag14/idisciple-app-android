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
import android.widget.TextView;

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
    @BindView(R.id.tvFamilyGroupName) TextView tvFamilyGroupName;

    private MainAppScreenActivity mActivity;
    private List<FamilyGroup> mData;
    private CommunityGroupAdapter mAdaper;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_community_tab_groups, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mData = mActivity.mPresenter.mFamilyGroupRepository.getContentList();

        mAdaper = new CommunityGroupAdapter(getChildFragmentManager());
        viewpager.setAdapter(mAdaper);
        tabStrip.setViewPager(viewpager);

        // Show First Family Group Name
        tvFamilyGroupName.setText(mData.get(0).getFamilyGroupName());

        tabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tvFamilyGroupName.setText(mAdaper.getPageTitle(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

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
            Bundle bundle = new Bundle();
            bundle.putString("familyGroupId", mData.get(position).getId());
            return newInstance(ContactListPerGroupFragment.class, bundle);
        }
    }
}

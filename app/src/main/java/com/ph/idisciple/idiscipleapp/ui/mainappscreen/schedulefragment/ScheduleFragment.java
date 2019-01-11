package com.ph.idisciple.idiscipleapp.ui.mainappscreen.schedulefragment;

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
import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;
import com.ph.idisciple.idiscipleapp.data.local.repository.Schedule.ScheduleRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import java.util.List;

import butterknife.BindView;

public class ScheduleFragment extends BaseFragment {

    @BindView(R.id.tabStrip) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewpager) ViewPager viewpager;

    private MainAppScreenActivity mActivity;
    private List<Schedule> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_schedule, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mData = mActivity.mPresenter.mScheduleRepository.getContentList();

        viewpager.setAdapter(new SchedulePagerAdapter(getChildFragmentManager()));
        tabStrip.setViewPager(viewpager);

        return rootView;
    }

    public class SchedulePagerAdapter extends FragmentPagerAdapter {

        public SchedulePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mData.get(position).getScheduleDate();
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

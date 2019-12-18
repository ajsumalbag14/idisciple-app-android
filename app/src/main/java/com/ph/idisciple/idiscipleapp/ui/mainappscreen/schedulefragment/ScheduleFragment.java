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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class ScheduleFragment extends BaseFragment {

    @BindView(R.id.tabStrip) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewpager) ViewPager viewpager;

    private MainAppScreenActivity mActivity;
    private List<String> arrConferenceDates = new ArrayList<>();
    private int currentTodayPositionTab = -1;

    private String dateTodayString;
    private Calendar calendarDateToday = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_schedule, container, false);
        bind(rootView);

        showRefreshList();
        dateTodayString = formatter.format(Calendar.getInstance().getTime());
        mActivity = (MainAppScreenActivity) getActivity();

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        showRefreshList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateScheduleList(RefreshScheduleListEvent event){
        showRefreshList();
    }

    private void showRefreshList(){
        if(mActivity == null || mActivity.mPresenter == null) return;
        if(mActivity.mPresenter.mScheduleDateList != null) {
            arrConferenceDates = new ArrayList<>();
            arrConferenceDates.addAll(mActivity.mPresenter.mScheduleDateList);
        }

        viewpager.setAdapter(new SchedulePagerAdapter(getChildFragmentManager()));
        tabStrip.setViewPager(viewpager);

        // Adjust Tab Text (So it won't be cut)
        LinearLayout tabsContainer = (LinearLayout) tabStrip.getChildAt(0);
        for (int i=0; i < tabsContainer.getChildCount(); i++) {
            TextView tab = (TextView)tabsContainer.getChildAt(i);
            tab.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
        }
    }

    public class SchedulePagerAdapter extends FragmentPagerAdapter {

        public SchedulePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String selectedDate = arrConferenceDates.get(position);
            Calendar calendarDateParsed = Calendar.getInstance();

            try {
                calendarDateToday.setTime(formatter.parse(dateTodayString));

                calendarDateParsed = Calendar.getInstance();
                calendarDateParsed.setTime(formatter.parse(selectedDate));

                if(selectedDate.equals(dateTodayString)) {
                    currentTodayPositionTab = position;
                    viewpager.setCurrentItem(currentTodayPositionTab);
                    return "  TODAY   ";
                } else if(calendarDateParsed.before(calendarDateToday)){
                    calendarDateToday.add(Calendar.DATE, -1);

                    if(selectedDate.equals(formatter.format(calendarDateToday.getTime())))
                        return "YESTERDAY ";
                    else
                        return selectedDate;
                } else if(calendarDateParsed.after(calendarDateToday)){
                    calendarDateToday.add(Calendar.DATE, +1);

                    if(selectedDate.equals(formatter.format(calendarDateToday.getTime())))
                        return "TOMORROW ";
                    else
                        return selectedDate;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return  selectedDate;
        }

        @Override
        public int getCount() {
            return arrConferenceDates.size();
        }

        @Override
        public Fragment getItem(int position) {
            String selectedDate = arrConferenceDates.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("selectedDate", selectedDate);
            bundle.putBoolean("isToday", currentTodayPositionTab == position);
            return newInstance(ScheduleListFragment.class, bundle);
        }
    }
}
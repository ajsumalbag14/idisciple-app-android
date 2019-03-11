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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class ScheduleFragment extends BaseFragment {

    @BindView(R.id.tabStrip) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewpager) ViewPager viewpager;

    private MainAppScreenActivity mActivity;
    private String[] arrConferenceDates = {"05/21/2019", "05/22/2019", "05/23/2019", "05/24/2019"}; //{"2019-03-05", "2019-03-06", "2019-03-07", "2019-03-08"};
    private int currentTodayPositionTab = -1;

    private String dateTodayString;
    private Calendar calendarDateToday = Calendar.getInstance();
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_schedule, container, false);
        bind(rootView);

        dateTodayString = formatter.format(Calendar.getInstance().getTime());
        mActivity = (MainAppScreenActivity) getActivity();

        viewpager.setAdapter(new SchedulePagerAdapter(getChildFragmentManager()));
        tabStrip.setViewPager(viewpager);

        // Adjust Tab Text (So it won't be cut)
        LinearLayout tabsContainer = (LinearLayout) tabStrip.getChildAt(0);
        for (int i=0; i < tabsContainer.getChildCount(); i++) {
            TextView tab = (TextView)tabsContainer.getChildAt(i);
            tab.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
        }

        return rootView;
    }

    public class SchedulePagerAdapter extends FragmentPagerAdapter {

        public SchedulePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String selectedDate = arrConferenceDates[position];
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
            return arrConferenceDates.length;
        }

        @Override
        public Fragment getItem(int position) {
            String selectedDate = arrConferenceDates[position];
            Bundle bundle = new Bundle();
            bundle.putString("selectedDate", selectedDate);
            bundle.putBoolean("isToday", currentTodayPositionTab == position);
            return newInstance(ScheduleListFragment.class, bundle);
        }
    }

}

package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;
import com.ph.idisciple.idiscipleapp.widgets.NonSwipeableViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommunityTabGroupsFragment extends BaseFragment {

    @BindView(R.id.tvFamilyGroup1stDigit) TextView tvFamilyGroup1stDigit;
    @BindView(R.id.tvFamilyGroup2ndDigit) TextView tvFamilyGroup2ndDigit;
    @BindView(R.id.viewpagerGroups) NonSwipeableViewPager viewpager;
    @BindView(R.id.tvFamilyGroupName) TextView tvFamilyGroupName;

    private MainAppScreenActivity mActivity;
    private List<FamilyGroup> mData;
    private CommunityGroupAdapter mAdapter;
    private int totalFgCount = 0;
    private int max1stDigit = 0;
    private int fg1stDigit = 0;
    private int fg2ndDigit = 1;
    private int currentItemCount = 1;

    @OnClick(R.id.ivFamilyGroup1stDigitDown)
    public void onDeductFamilyGroup1stDigit() {
        if (fg1stDigit > 0)
            fg1stDigit--;

        currentItemCount = (fg1stDigit * 10) + fg2ndDigit;

        if (currentItemCount == 0) {
            fg2ndDigit = 1;
            currentItemCount = 1;
            tvFamilyGroup2ndDigit.setText(String.valueOf(fg2ndDigit));
        }

        tvFamilyGroup1stDigit.setText(String.valueOf(fg1stDigit));
        updateFamilyGroupScreen();
    }

    @OnClick(R.id.ivFamilyGroup1stDigitUp)
    public void onAddFamilyGroup1stDigit() {
        if (fg1stDigit < max1stDigit)
            fg1stDigit++;

        currentItemCount = (fg1stDigit * 10) + fg2ndDigit;
        if (currentItemCount > totalFgCount) {
            tvFamilyGroup2ndDigit.setText(String.valueOf(totalFgCount).substring(1));
            fg2ndDigit = Integer.valueOf(tvFamilyGroup2ndDigit.getText().toString());
        }

        currentItemCount = (fg1stDigit * 10) + fg2ndDigit;
        tvFamilyGroup1stDigit.setText(String.valueOf(fg1stDigit));
        updateFamilyGroupScreen();
    }

    @OnClick(R.id.ivFamilyGroup2ndDigitDown)
    public void onDeductFamilyGroup2ndDigit() {
        if (fg2ndDigit > 1)
            fg2ndDigit--;

        currentItemCount = (fg1stDigit * 10) + fg2ndDigit;
        tvFamilyGroup2ndDigit.setText(String.valueOf(fg2ndDigit));
        updateFamilyGroupScreen();
    }

    @OnClick(R.id.ivFamilyGroup2ndDigitUp)
    public void onAddFamilyGroup2ndDigit() {
        int maxDigit = 9;
        if (fg1stDigit > max1stDigit - 1)
            maxDigit = totalFgCount % (fg1stDigit * 10);

        if (fg2ndDigit < maxDigit)
            fg2ndDigit++;

        currentItemCount = (fg1stDigit * 10) + fg2ndDigit;
        tvFamilyGroup2ndDigit.setText(String.valueOf(fg2ndDigit));
        updateFamilyGroupScreen();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_community_tab_groups, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mData = mActivity.mPresenter.mFamilyGroupRepository.getContentList();
        totalFgCount = mData.size();
        max1stDigit = totalFgCount / 10;

        mAdapter = new CommunityGroupAdapter(getChildFragmentManager());
        viewpager.setAdapter(mAdapter);

        // Show First Family Group Name
        if (mData != null)
            tvFamilyGroupName.setText(mData.get(0).getFamilyGroupName());

        return rootView;
    }


    private void updateFamilyGroupScreen() {
        tvFamilyGroupName.setText(mData.get(currentItemCount - 1).getFamilyGroupName());
        viewpager.setCurrentItem(currentItemCount - 1);
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

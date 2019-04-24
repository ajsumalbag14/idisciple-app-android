package com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;
import com.wagnerandade.coollection.query.order.Order;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wagnerandade.coollection.Coollection.contains;
import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class CommunityTabDelegatesFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;
    @BindView(R.id.llSearchNamePlaceholder) LinearLayout llSearchNamePlaceholder;
    @BindView(R.id.etSearchName) EditText etSearchName;

    @OnClick(R.id.llSearchName)
    public void onSearchNameClick(){
        toggleEditSearchNameAsActive(true);
    }

    @OnClick(R.id.llSearchNamePlaceholder)
    public void onSearchNamePlaceholderClick(){
        toggleEditSearchNameAsActive(true);
    }

    @OnClick(R.id.tvSearchName)
    public void onSearchNameTextClick(){
        toggleEditSearchNameAsActive(true);
    }

    @OnClick(R.id.clLayout)
    public void onOuterClick(){
        toggleEditSearchNameAsActive(false);
    }

    @OnClick(R.id.rvList)
    public void onListOnClick(){
        toggleEditSearchNameAsActive(false);
    }

    private MainAppScreenActivity mActivity;
    private List<Profile> mAllContactList;
    private List<Profile> mFilteredContactList;
    private LinearLayoutManager mLinearLayoutManager;
    private AttendeesAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_community_tab_delegates, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mAllContactList = mActivity.mPresenter.mAttendeesRepository.getContentList();
        mFilteredContactList = from(mAllContactList).orderBy("getUserFullNameCapslock", Order.ASC).all();

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        mAdapter = new AttendeesAdapter(mActivity, mFilteredContactList);
        rvList.setAdapter(mAdapter);

        etSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

               if(etSearchName.getText().toString().length() > 0){
                   String filterText = etSearchName.getText().toString().toUpperCase();
                   mFilteredContactList = from(mAllContactList).where("getUserFullNameCapslock", contains(filterText)).or("getUserNickNameCapslock", contains(filterText)).orderBy("getUserFullNameCapslock", Order.ASC).all();
                   mAdapter = new AttendeesAdapter(mActivity, mFilteredContactList);
                   rvList.setAdapter(mAdapter);
               } else {
                   mAdapter = new AttendeesAdapter(mActivity, mAllContactList);
                   rvList.setAdapter(mAdapter);
               }
            }
        });

        etSearchName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus && etSearchName.getText().toString().length() == 0)
                        toggleEditSearchNameAsActive(false);
                }
        });

        return rootView;
    }


    private void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSearchName, InputMethodManager.SHOW_FORCED);
    }

    private void toggleEditSearchNameAsActive(boolean isActive){
        llSearchNamePlaceholder.setVisibility(isActive ? View.GONE : View.VISIBLE);
        etSearchName.setVisibility(isActive ? View.VISIBLE : View.GONE);
        if(isActive) {
            etSearchName.requestFocus();
            showKeyboard();
        }
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
    public void onUpdateFavorite(RefreshFavoriteEvent event){
        mActivity = (MainAppScreenActivity) getActivity();
        mAdapter = new AttendeesAdapter(mActivity, mFilteredContactList);
        rvList.setAdapter(mAdapter);
    }
}

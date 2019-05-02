package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.resourcestab;

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
import com.ph.idisciple.idiscipleapp.data.local.model.Resource;
import com.ph.idisciple.idiscipleapp.data.local.repository.Resources.ResourcesRepository;
import com.ph.idisciple.idiscipleapp.ui.BaseFragment;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.MainAppScreenActivity;
import com.wagnerandade.coollection.query.order.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wagnerandade.coollection.Coollection.contains;
import static com.wagnerandade.coollection.Coollection.from;

public class MoreTabResourcesFragment extends BaseFragment {

    @BindView(R.id.rvList) RecyclerView rvList;
    @BindView(R.id.llSearchFilePlaceholder) LinearLayout llSearchFilePlaceholder;
    @BindView(R.id.etSearchFile) EditText etSearchFile;

    @OnClick(R.id.llSearchFile)
    public void onSearchFileClick(){
        toggleEditSearchNameAsActive(true);
    }

    @OnClick(R.id.llSearchFilePlaceholder)
    public void onSearchFilePlaceholderClick(){
        toggleEditSearchNameAsActive(true);
    }

    @OnClick(R.id.tvSearchFile)
    public void onSearchFileTextClick(){
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
    private LinearLayoutManager mLinearLayoutManager;
    private ResourceAdapter mAdapter;

    private ResourcesRepository mResourcesRepository;
    private List<Resource> mAllResourceList;
    private List<Resource> mFilteredResourceList;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_more_tab_resources, container, false);
        bind(rootView);

        mActivity = (MainAppScreenActivity) getActivity();
        mResourcesRepository = new ResourcesRepository();
        mAllResourceList = from(mResourcesRepository.getContentList()).orderBy("getResourceTitleCapslock", Order.ASC).all();
        mFilteredResourceList = from(mAllResourceList).orderBy("getResourceTitleCapslock", Order.ASC).all();

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        rvList.setLayoutManager(mLinearLayoutManager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);

        mAdapter = new ResourceAdapter(mActivity, mFilteredResourceList);
        rvList.setAdapter(mAdapter);

        etSearchFile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int deletedCharacter, int enteredCharacter) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(etSearchFile.getText().toString().length() > 0){
                    mFilteredResourceList = from(mAllResourceList).where("getResourceTitleCapslock", contains(etSearchFile.getText().toString().toUpperCase())).orderBy("getResourceTitleCapslock", Order.ASC).all();
                    mAdapter = new ResourceAdapter(mActivity, mFilteredResourceList);
                    rvList.setAdapter(mAdapter);
                } else {
                    mAdapter = new ResourceAdapter(mActivity, mAllResourceList);
                    rvList.setAdapter(mAdapter);
                }
            }
        });

        etSearchFile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus && etSearchFile.getText().toString().length() == 0)
                    toggleEditSearchNameAsActive(false);
            }
        });

        return rootView;
    }

    private void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSearchFile, InputMethodManager.SHOW_FORCED);
    }

    private void toggleEditSearchNameAsActive(boolean isActive){
        llSearchFilePlaceholder.setVisibility(isActive ? View.GONE : View.VISIBLE);
        etSearchFile.setVisibility(isActive ? View.VISIBLE : View.GONE);
        if(isActive) {
            etSearchFile.requestFocus();
            showKeyboard();
        }
    }
}

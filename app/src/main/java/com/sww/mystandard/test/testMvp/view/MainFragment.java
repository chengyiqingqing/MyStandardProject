package com.sww.mystandard.test.testMvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sww.mystandard.R;
import com.sww.mystandard.base.mvp.fragment.MvpBaseFragment;
import com.sww.mystandard.test.testMvp.contract.MainContract;
import com.sww.mystandard.test.testMvp.presenter.MainPresenter;

public class MainFragment extends MvpBaseFragment<MainPresenter, MainContract.Presenter> implements MainContract.View {

    private View root;
    private Button buttonClick;

    public static MainFragment getInstance(String word){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("word",word);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root!=null){
            ViewGroup parent = (ViewGroup) root.getParent();
            if (parent!=null) parent.removeView(root);
            return root;
        }
        root =inflater.inflate(R.layout.fragment_root,container,false);
        initView();
        mPresenter.initData(getArguments());
        return root;
    }

    private void initView() {
        buttonClick = root.findViewById(R.id.button_click);
    }

    @Override
    public void updateViewByData(String tip) {
        buttonClick.setText(tip);
    }

}

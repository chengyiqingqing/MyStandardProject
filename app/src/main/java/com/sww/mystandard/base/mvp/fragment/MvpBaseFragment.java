package com.sww.mystandard.base.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sww.mystandard.base.mvp.MvpView;
import com.sww.mystandard.base.mvp.presenter.BasePresenter;
import com.sww.mystandard.base.mvp.presenter.MvpPresenter;

/**
 * 初始化MvpPresenter;
 *
 * 1.初始化MvpPresenter;
 *
 * @param <V>
 * @param <U>
 */
public class MvpBaseFragment<V extends BasePresenter & MvpPresenter,U extends MvpPresenter>
    extends BaseFragment implements MvpView<U> {

    protected U mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean checkFragmentEnable() {
        return false;
    }

    @Override
    public void finishActivity() {

    }

    @Override
    public void setPresenter(MvpPresenter mvpPresenter) {

    }

}

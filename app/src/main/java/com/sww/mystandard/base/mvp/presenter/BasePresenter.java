package com.sww.mystandard.base.mvp.presenter;

import com.sww.mystandard.base.mvp.MvpView;

/**
 * 用来和Fragment生命周期进行绑定；
 * @param <V> 泛型：随便什么fragment;
 */
public abstract class BasePresenter<V extends MvpView> {

    protected V mvpView;

    public void attachView(V view) {
        mvpView = view;
    }

    public void detachView() {
        mvpView = null;
    }

    /**
     * 检查当前Fragment是否可用；
     */
    public boolean checkFragmentEnable() {
        return mvpView != null && mvpView.checkFragmentEnable();
    }

}

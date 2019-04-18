package com.sww.mystandard.base.mvp;

import android.content.Context;
import com.sww.mystandard.base.mvp.presenter.MvpPresenter;

public interface MvpView<T extends MvpPresenter>{

    /**
     * 1.获取当前上下文；
     */
    Context getContext();

    /**
     * 2.检查当前fragment是否可用；
     */
    boolean checkFragmentEnable();

    /**
     * 结束当前activity;
     */
    void finishActivity();

    /**
     * 自定义presenter;
     */
    void setPresenter(T t);

}

package com.sww.mystandard.base.mvp.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.sww.mystandard.base.mvp.MvpView;
import com.sww.mystandard.base.mvp.presenter.BasePresenter;
import com.sww.mystandard.base.mvp.presenter.MvpPresenter;
import com.sww.mystandard.base.utils.InstanceUtils;

/**
 * 初始化MvpPresenter;
 * <p>
 * 1.初始化具体类ConcretePresenter
 *
 * @param <T>  作为Presenter实例，presenter实例必须继承BasePresenter并且必须实现Contract接口，也就是U
 * @param <U> Contract接口
 */
public class MvpBaseFragment<T extends BasePresenter & MvpPresenter, U extends MvpPresenter>
        extends BaseFragment implements MvpView<U> {

    protected U mPresenter;

    /**
     * 1.1 初始化具体类ConcretePresenter;
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) return;
        // 反射创建这些对象；
        T presenterInstance = InstanceUtils.getInstance(this, InstanceUtils.POSITION_FIRST);
        initPresenter(presenterInstance);
    }

    /**
     * 1.2 真正初始化presenter
     */
    private void initPresenter(Object presenterInstance) {
        if (presenterInstance == null) {
            throw new RuntimeException("presenter is null");
        }
        if (!(presenterInstance instanceof BasePresenter)) {
            throw new RuntimeException("presenter 实例需要继承 BasePresenter");
        }
        if (!(presenterInstance instanceof MvpPresenter)) {
            throw new RuntimeException("presenter 实例需要实现 MvpPresenter");
        }
        this.mPresenter = (U) presenterInstance;
        ((BasePresenter) presenterInstance).attachView(this);
    }

    /**
     * 2.1 检查当前Fragment是否可用；
     */
    @Override
    public boolean checkFragmentEnable() {
        FragmentActivity activity = getActivity();
        if (activity != null || activity.isFinishing() || isDetached()) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            return !activity.isDestroyed();
        return true;
    }

    /**
     * 2.2 关闭当前Activity
     */
    @Override
    public void finishActivity() {
        if (getActivity() != null) getActivity().finish();
    }

    /**
     * 2.3 自定义Presenter;
     */
    @Override
    public void setPresenter(U mvpPresenter) {
        initPresenter(mvpPresenter);
    }

    /**
     * 获取上下文；
     */
    public Activity getContext() {
        return getActivity();
    }

    /**
     * 1.3 销毁view的监听；
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            ((T) mPresenter).detachView();
    }

}

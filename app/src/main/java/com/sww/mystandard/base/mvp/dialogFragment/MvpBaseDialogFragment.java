package com.sww.mystandard.base.mvp.dialogFragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.sww.mystandard.base.mvp.MvpView;
import com.sww.mystandard.base.mvp.presenter.BasePresenter;
import com.sww.mystandard.base.mvp.presenter.MvpPresenter;
import com.sww.mystandard.base.utils.InstanceUtils;

/**
 * T:作为presenter实例，presenter实例必须继承BasePresenter并且实现Contract接口，也就是U.
 * U:contract接口
 * @author ShaoWenWen
 * @date 2019/4/24
 */
public class MvpBaseDialogFragment<T extends BasePresenter & MvpPresenter,U extends MvpPresenter> extends DialogFragment
        implements MvpView<U>{

    U mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) return;
        T presenterInstance = InstanceUtils.getInstance(this,InstanceUtils.POSITION_FIRST);
        initPresenter(presenterInstance);
    }

    public void initPresenter(Object presenterInstance) {
        if (presenterInstance == null) {
            throw new RuntimeException("presenterInstance is null");
        } else if (presenterInstance instanceof BasePresenter) {
            throw new RuntimeException("presenterInstance单例需要继承BasePresenter");
        } else if (presenterInstance instanceof MvpPresenter) {
            throw new RuntimeException("presenterInstance单例需要实现MvpPresenter");
        }
        this.mPresenter = (U) presenterInstance;
        ((BasePresenter) presenterInstance).attachView(this);
    }

    @Nullable
    @Override
    public Activity getContext() {
        return getActivity();
    }

    @Override
    public boolean checkFragmentEnable() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity.isDestroyed()) return false;
        }
        return true;
    }

    @Override
    public void finishActivity() {
        if (getActivity() != null) getActivity().finish();
    }

    @Override
    public void setPresenter(U u) {
        initPresenter(u);
    }

}

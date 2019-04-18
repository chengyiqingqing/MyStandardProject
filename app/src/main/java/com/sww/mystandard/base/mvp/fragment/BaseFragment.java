package com.sww.mystandard.base.mvp.fragment;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.sww.mystandard.base.activity.OnBackPressedListener;
import com.sww.mystandard.base.activity.OnKeyDownListener;
import com.sww.mystandard.base.activity.OnKeyListener;

/**
 * 父类Fragment，做子类Fragment通用操作;
 * 1.对返回键的拦截监听器，进行添加，移除，及拦截处理
 * 2.对根布局设置paddingTop，预留出状态栏的高度；
 */
public class BaseFragment extends Fragment implements OnKeyListener, OnBackPressedListener {

    /**
     * 1.1返回键的拦截，被子类重写。
     *
     * @return 子类返回true代表拦截，可书写拦截后的逻辑；
     */
    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 1.2按键监听；
     *
     * @param keyCode 按键类型：返回键等；
     * @param event   按下，滑动，抬起；
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && onBackPressed();
    }

    /**
     * 1.3 添加拦截返回键的监听；
     */
    public void setKeyDownListenerEnable() {
        if (getActivity() instanceof OnKeyDownListener) {
            ((OnKeyDownListener) getActivity()).addOnKeyDownListener(this);
        } else {
            throw new RuntimeException("Activity must implement OnKeyDownListener");
        }
    }

    /**
     * 2.以自身fragment为容器，添加【碎片Fragment】;
     */
    public void addComponentFragment(@IdRes int layoutId, Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .add(layoutId, fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 2.2 把自己移除；
     */
    public void removeSelfFragment() {
        if (getActivity() != null && getFragmentManager() != null)
            getFragmentManager()
                    .beginTransaction()
                    .remove(this)
                    .commitAllowingStateLoss();
    }

    /**
     * 3.1 状态栏浸透，根布局设置paddingTop，预留出距离顶部高度；
     * @param rootView
     */
    public void setStatusBarPaddingTop(View rootView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 版本大于19；
            rootView.setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

    /**
     * 3.2 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? Resources.getSystem().getDimensionPixelSize(resourceId) : 0;
    }

    /**
     * 1.4 在当前fragment销毁时，将返回键监听移除；
     */
    @Override
    public void onDetach() {
        super.onDetach();
        if (getActivity() instanceof OnKeyDownListener) {
            ((OnKeyDownListener) getActivity()).removeOnKeyDownListener(this);
        }
    }
}

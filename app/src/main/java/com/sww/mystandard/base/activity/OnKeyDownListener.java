package com.sww.mystandard.base.activity;

/**
 * 【back键监听器】的添加和移除；
 */
public interface OnKeyDownListener {

    void addOnKeyDownListener(OnKeyListener onKeyListener);

    void removeOnKeyDownListener(OnKeyListener onKeyListener);

}

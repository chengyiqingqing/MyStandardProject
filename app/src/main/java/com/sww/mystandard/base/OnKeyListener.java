package com.sww.mystandard.base;

import android.view.KeyEvent;

/**
 * 监听器：按下的事件
 */
public interface OnKeyListener {

    boolean onKeyDown(int keyCode, KeyEvent event);

}

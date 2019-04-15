package com.sww.mystandard.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity的基础类
 */
public class BaseActivity extends AppCompatActivity implements OnKeyDownListener {

    // TODO: 这里是为什么？ 2019/4/15
    private List<OnKeyListener> onKeyDownListeners = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param onKeyListener
     */
    @Override
    public void addOnKeyDownListener(OnKeyListener onKeyListener) {
        onKeyDownListeners.add(0, onKeyListener);
    }

    @Override
    public void removeOnKeyDownListener(OnKeyListener onKeyListener) {
        if (onKeyDownListeners.contains(onKeyListener)) onKeyDownListeners.remove(onKeyListener);
    }

    /**
     * 系统的物理键回调；
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        for (OnKeyListener listener : onKeyDownListeners) {
            if (listener.onKeyDown(keyCode, event)) return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

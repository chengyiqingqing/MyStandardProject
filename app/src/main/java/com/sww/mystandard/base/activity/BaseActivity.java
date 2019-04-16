package com.sww.mystandard.base.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

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
     * 1.1添加onKeyDown的监听；
     */
    @Override
    public void addOnKeyDownListener(OnKeyListener onKeyListener) {
        onKeyDownListeners.add(0, onKeyListener);
    }

    /**
     * 1.2移除onKeyDown的监听；
     * @param onKeyListener
     */
    @Override
    public void removeOnKeyDownListener(OnKeyListener onKeyListener) {
        if (onKeyDownListeners.contains(onKeyListener)) onKeyDownListeners.remove(onKeyListener);
    }

    /**
     * 1.3系统的物理键回调；用于遍历添加的每一个OnKeyListener的onKeyDown方法;
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        for (OnKeyListener listener : onKeyDownListeners) {
            if (listener.onKeyDown(keyCode, event)) return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void initTranslucentStatusBar(boolean isLightBar) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){// sdk版本号>=23;
            if (isLightBar) getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            else getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){// sdk版本号<23且sdk版本号>=21；
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){// sdk版本号大于18小于21
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 对window进行设置Flags
        }
        // 隐藏ActionBar
        if (getSupportActionBar()!=null) getSupportActionBar().hide();
    }



}

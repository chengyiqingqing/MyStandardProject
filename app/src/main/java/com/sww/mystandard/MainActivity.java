package com.sww.mystandard;

import android.os.Bundle;

import com.sww.mystandard.base.activity.BaseActivity;
import com.sww.mystandard.test.ComponentFragment;
import com.sww.mystandard.test.RootFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRootFragment(R.id.frame_root, RootFragment.getInstance());
        addComponentFragment(R.id.frame_component, ComponentFragment.getInstance());
    }

}

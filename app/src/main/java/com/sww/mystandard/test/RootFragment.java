package com.sww.mystandard.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.sww.mystandard.R;

public class RootFragment extends Fragment {

    private View rootView;

    public static RootFragment getInstance(){
        return new RootFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView!=null){
            ViewGroup viewParent = (ViewGroup) rootView.getParent();
            if (viewParent!=null) viewParent.removeView(rootView);
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_root,container,false);
        return rootView;
    }

}

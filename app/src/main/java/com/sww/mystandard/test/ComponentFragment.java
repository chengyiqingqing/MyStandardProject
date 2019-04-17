package com.sww.mystandard.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sww.mystandard.R;

public class ComponentFragment extends Fragment {

    private View rootView;

    public static ComponentFragment getInstance(){
        return new ComponentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView!=null){
            ViewGroup viewParent = (ViewGroup) rootView.getParent();
            if (viewParent!=null) viewParent.removeView(rootView);
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_component,container,false);
        return rootView;
    }

}

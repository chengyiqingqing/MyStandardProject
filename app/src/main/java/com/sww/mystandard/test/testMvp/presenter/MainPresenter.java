package com.sww.mystandard.test.testMvp.presenter;

import android.os.Bundle;

import com.sww.mystandard.base.mvp.presenter.BasePresenter;
import com.sww.mystandard.test.testMvp.contract.MainContract;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private String word = "hello world";

    @Override
    public void initData(Bundle bundle) {
        word = bundle.getString("word");
        mvpView.updateViewByData(word);
    }

}

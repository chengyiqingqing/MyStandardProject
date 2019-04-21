package com.sww.mystandard.test.testMvp.contract;

import android.os.Bundle;

import com.sww.mystandard.base.mvp.MvpView;
import com.sww.mystandard.base.mvp.presenter.MvpPresenter;

public interface MainContract {

    interface Presenter extends MvpPresenter{

        /**
         * 更新Model数据；
         */
        void initData(Bundle bundle);
    }

    interface View extends MvpView<Presenter>{

        /**
         * 更新View显示；
         */
        void updateViewByData(String tip);

    }

}

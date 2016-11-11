package com.bill.recreation.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.bill.recreation.mvp.view.base.BaseView;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface BasePresenter {

//    void onResume();

    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();

}

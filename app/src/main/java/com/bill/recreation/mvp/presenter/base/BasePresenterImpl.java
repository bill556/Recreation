package com.bill.recreation.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.bill.recreation.mvp.view.base.BaseView;
import com.bill.recreation.listener.RequestCallBack;
import com.bill.recreation.utils.MyUtils;

import rx.Subscription;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter, RequestCallBack<E> {
    protected T mView;
    protected Subscription mSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        MyUtils.cancelSubscription(mSubscription);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        // TODO?
        mView = (T) view;
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }

}

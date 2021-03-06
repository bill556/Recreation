package com.bill.recreation.mvp.interactor;

import com.bill.recreation.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface PhotoInteractor<T> {
    Subscription loadPhotos(RequestCallBack<T> listener, int size, int page);
}

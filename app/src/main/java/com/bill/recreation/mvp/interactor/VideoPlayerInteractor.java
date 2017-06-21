package com.bill.recreation.mvp.interactor;

import com.bill.recreation.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface VideoPlayerInteractor<T> {
    Subscription loadVideos(RequestCallBack<T> listener, int page, int size);
}

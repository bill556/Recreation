package com.bill.recreation.mvp.interactor;

import com.bill.recreation.greendao.NewsChannelTable;
import com.bill.recreation.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface NewsChannelInteractor<T> {
    Subscription lodeNewsChannels(RequestCallBack<T> callback);

    void swapDb(int fromPosition,int toPosition);

    void updateDb(NewsChannelTable newsChannel, boolean isChannelMine);
}

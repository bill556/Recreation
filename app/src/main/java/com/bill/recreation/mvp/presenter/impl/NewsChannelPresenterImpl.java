package com.bill.recreation.mvp.presenter.impl;

import com.bill.recreation.common.Constants;
import com.bill.recreation.event.ChannelChangeEvent;
import com.bill.recreation.mvp.presenter.NewsChannelPresenter;
import com.bill.recreation.mvp.presenter.base.BasePresenterImpl;
import com.bill.recreation.greendao.NewsChannelTable;
import com.bill.recreation.mvp.interactor.impl.NewsChannelInteractorImpl;
import com.bill.recreation.mvp.view.NewsChannelView;
import com.bill.recreation.utils.RxBus;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class NewsChannelPresenterImpl extends BasePresenterImpl<NewsChannelView,
        Map<Integer, List<NewsChannelTable>>> implements NewsChannelPresenter {

    private NewsChannelInteractorImpl mNewsChannelInteractor;
    private boolean mIsChannelChanged;

    @Inject
    public NewsChannelPresenterImpl(NewsChannelInteractorImpl newsChannelInteractor) {
        mNewsChannelInteractor = newsChannelInteractor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIsChannelChanged) {
            RxBus.getInstance().post(new ChannelChangeEvent());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNewsChannelInteractor.lodeNewsChannels(this);
    }

    @Override
    public void success(Map<Integer, List<NewsChannelTable>> data) {
        super.success(data);
        mView.initRecyclerViews(data.get(Constants.NEWS_CHANNEL_MINE), data.get(Constants.NEWS_CHANNEL_MORE));
    }

    @Override
    public void onItemSwap(int fromPosition, int toPosition) {
        mNewsChannelInteractor.swapDb(fromPosition, toPosition);
        mIsChannelChanged = true;
    }

    @Override
    public void onItemAddOrRemove(NewsChannelTable newsChannel, boolean isChannelMine) {
        mNewsChannelInteractor.updateDb(newsChannel, isChannelMine);
        mIsChannelChanged = true;
    }
}

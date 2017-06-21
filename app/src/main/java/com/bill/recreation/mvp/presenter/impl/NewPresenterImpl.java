package com.bill.recreation.mvp.presenter.impl;

import com.bill.recreation.mvp.interactor.impl.NewsInteractorImpl;
import com.bill.recreation.mvp.presenter.NewsPresenter;
import com.bill.recreation.mvp.presenter.base.BasePresenterImpl;
import com.bill.recreation.greendao.NewsChannelTable;
import com.bill.recreation.mvp.interactor.NewsInteractor;
import com.bill.recreation.mvp.view.NewsView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class NewPresenterImpl extends BasePresenterImpl<NewsView, List<NewsChannelTable>>
        implements NewsPresenter {

    private NewsInteractor<List<NewsChannelTable>> mNewsInteractor;

    @Inject
    public NewPresenterImpl(NewsInteractorImpl newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadNewsChannels();
    }

    private void loadNewsChannels() {
        mSubscription = mNewsInteractor.lodeNewsChannels(this);
    }

    @Override
    public void success(List<NewsChannelTable> data) {
        super.success(data);
        mView.initViewPager(data);
    }

    @Override
    public void onChannelDbChanged() {
        loadNewsChannels();
    }
}

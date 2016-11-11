package com.bill.recreation.mvp.presenter.impl;

import com.bill.recreation.mvp.entity.NewsDetail;
import com.bill.recreation.mvp.interactor.NewsDetailInteractor;
import com.bill.recreation.mvp.interactor.impl.NewsDetailInteractorImpl;
import com.bill.recreation.mvp.presenter.NewsDetailPresenter;
import com.bill.recreation.mvp.presenter.base.BasePresenterImpl;
import com.bill.recreation.mvp.view.NewsDetailView;

import javax.inject.Inject;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class NewsDetailPresenterImpl extends BasePresenterImpl<NewsDetailView, NewsDetail>
        implements NewsDetailPresenter {
    private NewsDetailInteractor<NewsDetail> mNewsDetailInteractor;
    private String mPostId;

    @Inject
    public NewsDetailPresenterImpl(NewsDetailInteractorImpl newsDetailInteractor) {
        mNewsDetailInteractor = newsDetailInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSubscription = mNewsDetailInteractor.loadNewsDetail(this, mPostId);

    }

    @Override
    public void success(NewsDetail data) {
        super.success(data);
        mView.setNewsDetail(data);
    }

    @Override
    public void setPosId(String postId) {
        mPostId = postId;
    }
}

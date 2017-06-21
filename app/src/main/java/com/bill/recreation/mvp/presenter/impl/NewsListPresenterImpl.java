package com.bill.recreation.mvp.presenter.impl;

import com.bill.recreation.mvp.entity.NewsSummary;
import com.bill.recreation.mvp.interactor.NewsListInteractor;
import com.bill.recreation.mvp.presenter.base.BasePresenterImpl;
import com.bill.recreation.common.LoadNewsType;
import com.bill.recreation.mvp.interactor.impl.NewsListInteractorImpl;
import com.bill.recreation.listener.RequestCallBack;
import com.bill.recreation.mvp.presenter.NewsListPresenter;
import com.bill.recreation.mvp.view.NewsListView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class NewsListPresenterImpl extends BasePresenterImpl<NewsListView, List<NewsSummary>>
        implements NewsListPresenter, RequestCallBack<List<NewsSummary>> {

    private NewsListInteractor<List<NewsSummary>> mNewsListInteractor;
    private String mNewsType;
    private String mNewsId;
    private int mStartPage;
    private boolean misFirstLoad;
    private boolean mIsRefresh = true;

    @Inject
    public NewsListPresenterImpl(NewsListInteractorImpl newsListInteractor) {
        mNewsListInteractor = newsListInteractor;
    }

    @Override
    public void onCreate() {
        if (mView != null) {
            loadNewsData();
        }
    }

    @Override
    public void beforeRequest() {
        if (!misFirstLoad) {
            mView.showProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if (mView != null) {
            int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_ERROR : LoadNewsType.TYPE_LOAD_MORE_ERROR;
            mView.setNewsList(null, loadType);
        }
    }

    @Override
    public void success(List<NewsSummary> items) {
        misFirstLoad = true;
        if (items != null) {
            mStartPage += 20;
        }

        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        if (mView != null) {
            mView.setNewsList(items, loadType);
            mView.hideProgress();
        }

    }

    @Override
    public void setNewsTypeAndId(String newsType, String newsId) {
        mNewsType = newsType;
        mNewsId = newsId;
    }

    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        loadNewsData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadNewsData();
    }

    private void loadNewsData() {
        mSubscription = mNewsListInteractor.loadNews(this, mNewsType, mNewsId, mStartPage);
    }
}

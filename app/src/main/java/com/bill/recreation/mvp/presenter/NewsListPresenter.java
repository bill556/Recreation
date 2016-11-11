package com.bill.recreation.mvp.presenter;

import com.bill.recreation.mvp.presenter.base.BasePresenter;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface NewsListPresenter extends BasePresenter {
    void setNewsTypeAndId(String newsType, String newsId);

    void refreshData();

    void loadMore();
}

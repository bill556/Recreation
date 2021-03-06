package com.bill.recreation.mvp.presenter;

import com.bill.recreation.mvp.presenter.base.BasePresenter;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface VideoPlayerPresenter extends BasePresenter {
    void refreshData();

    void loadMore();
}

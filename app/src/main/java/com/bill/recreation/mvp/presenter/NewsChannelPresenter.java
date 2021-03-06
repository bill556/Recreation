package com.bill.recreation.mvp.presenter;

import com.bill.recreation.greendao.NewsChannelTable;
import com.bill.recreation.mvp.presenter.base.BasePresenter;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface NewsChannelPresenter extends BasePresenter {
    void onItemSwap(int fromPosition, int toPosition);

    void onItemAddOrRemove(NewsChannelTable newsChannel, boolean isChannelMine);
}

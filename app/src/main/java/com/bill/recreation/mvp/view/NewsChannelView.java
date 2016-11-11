package com.bill.recreation.mvp.view;

import com.bill.recreation.greendao.NewsChannelTable;
import com.bill.recreation.mvp.view.base.BaseView;

import java.util.List;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface NewsChannelView extends BaseView {
    void initRecyclerViews(List<NewsChannelTable> newsChannelsMine, List<NewsChannelTable> newsChannelsMore);
}

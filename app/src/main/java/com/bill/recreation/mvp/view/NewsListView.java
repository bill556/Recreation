package com.bill.recreation.mvp.view;

import com.bill.recreation.mvp.entity.NewsSummary;
import com.bill.recreation.common.LoadNewsType;
import com.bill.recreation.mvp.view.base.BaseView;

import java.util.List;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface NewsListView extends BaseView {

    void setNewsList(List<NewsSummary> newsSummary, @LoadNewsType.checker int loadType);
}

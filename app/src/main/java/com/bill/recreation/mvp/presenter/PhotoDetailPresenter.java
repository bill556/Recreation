package com.bill.recreation.mvp.presenter;

import com.bill.recreation.common.PhotoRequestType;
import com.bill.recreation.mvp.presenter.base.BasePresenter;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public interface PhotoDetailPresenter extends BasePresenter {
    void handlePicture(String imageUrl, @PhotoRequestType.PhotoRequestTypeChecker int type);
}

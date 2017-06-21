package com.bill.recreation.mvp.interactor.impl;

import com.bill.recreation.App;
import com.bill.recreation.common.HostType;
import com.bill.recreation.listener.RequestCallBack;
import com.bill.recreation.mvp.entity.GirlData;
import com.bill.recreation.mvp.entity.PhotoGirl;
import com.bill.recreation.repository.network.RetrofitManager;
import com.bill.recreation.utils.TransformUtils;
import com.bill.recreation.R;
import com.bill.recreation.mvp.interactor.PhotoInteractor;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class PhotoInteractorImpl implements PhotoInteractor<List<PhotoGirl>> {

    @Inject
    public PhotoInteractorImpl() {
    }

    @Override
    public Subscription loadPhotos(final RequestCallBack<List<PhotoGirl>> listener, int size, int page) {
        return RetrofitManager.getInstance(HostType.GANK_GIRL_PHOTO)
                .getPhotoListObservable(size, page)
                .map(new Func1<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> call(GirlData girlData) {
                        return girlData.getResults();
                    }
                })
                .compose(TransformUtils.<List<PhotoGirl>>defaultSchedulers())
                .subscribe(new Subscriber<List<PhotoGirl>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(App.getAppContext().getString(R.string.load_error));
                    }

                    @Override
                    public void onNext(List<PhotoGirl> photoGirls) {
                        listener.success(photoGirls);
                    }
                })
                ;
    }
}

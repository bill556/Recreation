package com.bill.recreation.mvp.interactor.impl;

import com.bill.recreation.App;
import com.bill.recreation.R;
import com.bill.recreation.common.HostType;
import com.bill.recreation.listener.RequestCallBack;
import com.bill.recreation.mvp.entity.Video;
import com.bill.recreation.mvp.entity.VideoData;
import com.bill.recreation.mvp.interactor.VideoPlayerInteractor;
import com.bill.recreation.repository.network.RetrofitManager;
import com.bill.recreation.utils.TransformUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class VideoPlayerInteractorImpl implements VideoPlayerInteractor<List<Video>> {

    @Inject
    public VideoPlayerInteractorImpl() {
    }

    @Override
    public Subscription loadVideos(final RequestCallBack<List<Video>> listener, int page, int size) {
        return RetrofitManager.getInstance(HostType.KAI_YAN_VIDEO)
                .getVideoListObservable(page, size)
                .map(new Func1<VideoData, List<Video>>() {
                    @Override
                    public List<Video> call(VideoData videoData) {
                        return videoData.getItemList();
                    }
                })
                .compose(TransformUtils.<List<Video>>defaultSchedulers())
                .subscribe(new Subscriber<List<Video>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(App.getAppContext().getString(R.string.load_error));
                    }

                    @Override
                    public void onNext(List<Video> videos) {
                        listener.success(videos);
                    }
                });
    }
}

package com.bill.recreation.mvp.presenter.impl;

import com.bill.recreation.common.LoadNewsType;
import com.bill.recreation.listener.RequestCallBack;
import com.bill.recreation.mvp.entity.Video;
import com.bill.recreation.mvp.interactor.VideoPlayerInteractor;
import com.bill.recreation.mvp.interactor.impl.VideoPlayerInteractorImpl;
import com.bill.recreation.mvp.presenter.VideoPlayerPresenter;
import com.bill.recreation.mvp.presenter.base.BasePresenterImpl;
import com.bill.recreation.mvp.view.VideoPlayerView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class VideoPlayerPresenterImpl extends BasePresenterImpl<VideoPlayerView, List<Video>> implements VideoPlayerPresenter, RequestCallBack<List<Video>> {
    private VideoPlayerInteractor mVideoPlayerInteractor;
    private static int SIZE = 10;
    private int mStartPage = 1;
    private boolean misFirstLoad;
    private boolean mIsRefresh = true;

    @Inject
    public VideoPlayerPresenterImpl(VideoPlayerInteractorImpl videoInteractor) {
        mVideoPlayerInteractor = videoInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadVideoData();
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
            mView.setVideoList(null, loadType);
        }
    }

    @Override
    public void success(List<Video> items) {
        super.success(items);
        misFirstLoad = true;
        if (items != null) {
            mStartPage += 1;
        }

        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        if (mView != null) {
            mView.setVideoList(items, loadType);
            mView.hideProgress();
        }
    }

    @Override
    public void refreshData() {
        mStartPage = 1;
        mIsRefresh = true;
        loadVideoData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadVideoData();
    }

    private void loadVideoData() {
        mVideoPlayerInteractor.loadVideos(this, mStartPage, SIZE);
    }
}

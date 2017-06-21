package com.bill.recreation.mvp.view;

import com.bill.recreation.common.LoadNewsType;
import com.bill.recreation.mvp.entity.Video;
import com.bill.recreation.mvp.view.base.BaseView;

import java.util.List;

/**
 * author: Bill
 * created on: 17/3/7 上午10:12
 * description: 视频播放
 */
public interface VideoPlayerView extends BaseView {
    void setVideoList(List<Video> videos, @LoadNewsType.checker int loadType);
}

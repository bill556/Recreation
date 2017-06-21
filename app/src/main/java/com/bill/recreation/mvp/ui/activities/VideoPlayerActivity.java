package com.bill.recreation.mvp.ui.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bill.recreation.mvp.ui.adapter.VideoPlayerListAdapter;
import com.bill.recreation.R;
import com.bill.recreation.annotation.BindValues;
import com.bill.recreation.common.Constants;
import com.bill.recreation.common.LoadNewsType;
import com.bill.recreation.listener.OnItemClickListener;
import com.bill.recreation.mvp.entity.Video;
import com.bill.recreation.mvp.presenter.impl.VideoPlayerPresenterImpl;
import com.bill.recreation.mvp.ui.activities.base.BaseActivity;
import com.bill.recreation.mvp.view.VideoPlayerView;
import com.bill.recreation.utils.NetUtil;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Bill
 * created on: 17/3/7 上午10:11
 * description: 视频播放
 */
@BindValues(mIsHasNavigationView = true)
public class VideoPlayerActivity extends BaseActivity implements VideoPlayerView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.video_rv)
    RecyclerView mVideoRv;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.empty_view)
    TextView mEmptyView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.video_full_container)
    FrameLayout videoFullContainer;

    @Inject
    VideoPlayerPresenterImpl mVideoPresenter;
    @Inject
    VideoPlayerListAdapter mVideoListAdapter;
    @Inject
    Activity mActivity;

    private boolean mIsAllLoaded;

    ListVideoUtil listVideoUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        mBaseNavView = mNavView;

        initSwipeRefreshLayout();
        initRecyclerView();
        setAdapterItemClickEvent();
        initPresenter();
        listVideoUtil.setHideActionBar(true);
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.gplus_colors));
    }

    private void initRecyclerView() {
        mVideoRv.setHasFixedSize(true);
        mVideoRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mVideoRv.setItemAnimator(new DefaultItemAnimator());
        mVideoRv.setAdapter(mVideoListAdapter);
        setRvScrollEvent();
        listVideoUtil = new ListVideoUtil(this);
        listVideoUtil.setFullViewContainer(videoFullContainer);
        listVideoUtil.setHideStatusBar(true);
        mVideoListAdapter.setListVideoUtil(listVideoUtil);
    }

    private void setRvScrollEvent() {
        mVideoRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int[] lastVisibleItemPosition = ((StaggeredGridLayoutManager) layoutManager)
                        .findLastVisibleItemPositions(null);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (!mIsAllLoaded && visibleItemCount > 0 &&
                        (newState == RecyclerView.SCROLL_STATE_IDLE) &&
                        ((lastVisibleItemPosition[0] >= totalItemCount - 1) ||
                                (lastVisibleItemPosition[1] >= totalItemCount - 1))) {
                    mVideoPresenter.loadMore();
                    mVideoListAdapter.showFooter();
                    mVideoRv.scrollToPosition(mVideoListAdapter.getItemCount() - 1);
                }
            }

        });
    }

    private void setAdapterItemClickEvent() {
        mVideoListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(PhotoActivity.this, PhotoDetailActivity.class);
//                intent.putExtra(Constants.PHOTO_DETAIL, mPhotoListAdapter.getList().get(position).getUrl());
//                startActivity(view, intent);
            }
        });
    }

    private void startActivity(View view, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(mActivity, view, Constants.TRANSITION_ANIMATION_NEWS_PHOTOS);
            startActivity(intent, options.toBundle());
        } else {
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(mActivity, intent, options.toBundle());
        }
    }

    private void initPresenter() {
        mPresenter = mVideoPresenter;
        mPresenter.attachView(this);
        mPresenter.onCreate();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {
        mProgressBar.setVisibility(View.GONE);
        if (NetUtil.isNetworkAvailable()) {
            Snackbar.make(mVideoRv, message, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRefresh() {
        mVideoPresenter.refreshData();
    }

    @Override
    public void setVideoList(List<Video> videos, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                mSwipeRefreshLayout.setRefreshing(false);
                mVideoListAdapter.setList(videos);
                mVideoListAdapter.notifyDataSetChanged();
                checkIsEmpty(videos);
                mIsAllLoaded = false;
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                mSwipeRefreshLayout.setRefreshing(false);
                checkIsEmpty(videos);
                break;
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:
                mVideoListAdapter.hideFooter();
                if (videos == null || videos.size() == 0) {
                    mIsAllLoaded = true;
                    Snackbar.make(mVideoRv, getString(R.string.no_more), Snackbar.LENGTH_SHORT).show();
                } else {
                    mVideoListAdapter.addMore(videos);
                }
                break;
            case LoadNewsType.TYPE_LOAD_MORE_ERROR:
                mVideoListAdapter.hideFooter();
                break;
        }
    }

    private void checkIsEmpty(List<Video> photoGirls) {
        if (photoGirls == null && mVideoListAdapter.getList() == null) {
            mVideoRv.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mVideoRv.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.empty_view, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.empty_view:
                mSwipeRefreshLayout.setRefreshing(true);
                mVideoPresenter.refreshData();
                break;
            case R.id.fab:
                mVideoRv.getLayoutManager().scrollToPosition(0);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (listVideoUtil.backFromFull()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        listVideoUtil.releaseVideoPlayer();
        GSYVideoPlayer.releaseAllVideos();
        super.onDestroy();
    }
}

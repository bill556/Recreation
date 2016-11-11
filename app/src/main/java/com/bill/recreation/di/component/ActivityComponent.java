package com.bill.recreation.di.component;

import android.app.Activity;
import android.content.Context;


import com.bill.recreation.di.module.ActivityModule;
import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerActivity;
import com.bill.recreation.mvp.ui.activities.NewsActivity;
import com.bill.recreation.mvp.ui.activities.NewsChannelActivity;
import com.bill.recreation.mvp.ui.activities.NewsDetailActivity;
import com.bill.recreation.mvp.ui.activities.NewsPhotoDetailActivity;
import com.bill.recreation.mvp.ui.activities.PhotoActivity;
import com.bill.recreation.mvp.ui.activities.PhotoDetailActivity;

import dagger.Component;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsActivity newsActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsChannelActivity newsChannelActivity);

    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);

    void inject(PhotoActivity photoActivity);

    void inject(PhotoDetailActivity photoDetailActivity);
}

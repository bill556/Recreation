package com.bill.recreation.di.component;

import android.app.Activity;
import android.content.Context;

import com.bill.recreation.di.module.FragmentModule;
import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerFragment;
import com.bill.recreation.mvp.ui.fragment.NewsListFragment;

import dagger.Component;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsListFragment newsListFragment);
}

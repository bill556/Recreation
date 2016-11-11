package com.bill.recreation.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}

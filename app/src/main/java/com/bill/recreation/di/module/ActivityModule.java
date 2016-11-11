package com.bill.recreation.di.module;

import android.app.Activity;
import android.content.Context;


import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context ProvideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity ProvideActivity() {
        return mActivity;
    }
}

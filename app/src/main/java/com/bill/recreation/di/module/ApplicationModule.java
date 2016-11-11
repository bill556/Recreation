package com.bill.recreation.di.module;

import android.content.Context;


import com.bill.recreation.App;
import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@Module
public class ApplicationModule {
    private App mApplication;

    public ApplicationModule(App application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

}

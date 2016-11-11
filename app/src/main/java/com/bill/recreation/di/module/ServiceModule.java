package com.bill.recreation.di.module;

import android.app.Service;
import android.content.Context;

import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ContextLife("Service")
    public Context ProvideServiceContext() {
        return mService;
    }
}

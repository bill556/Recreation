package com.bill.recreation.di.component;

import android.content.Context;


import com.bill.recreation.di.module.ServiceModule;
import com.bill.recreation.di.scope.ContextLife;
import com.bill.recreation.di.scope.PerService;

import dagger.Component;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();
}

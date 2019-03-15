package com.grab.assignmentgrab.base;


import com.grab.assignmentgrab.di.component.ApplicationComponent;
import com.grab.assignmentgrab.di.component.DaggerApplicationComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Sumit on 14/03/19.
 */


public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }

}

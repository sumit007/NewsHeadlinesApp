package com.grab.assignmentgrab.di.component;

import android.app.Application;

import com.grab.assignmentgrab.base.BaseApplication;
import com.grab.assignmentgrab.di.module.ActivityBindingModule;
import com.grab.assignmentgrab.di.module.ApplicationModule;
import com.grab.assignmentgrab.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by Sumit on 14/03/19.
 */

@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {
    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}

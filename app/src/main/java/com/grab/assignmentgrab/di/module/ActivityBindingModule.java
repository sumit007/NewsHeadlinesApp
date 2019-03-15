package com.grab.assignmentgrab.di.module;

import com.grab.assignmentgrab.ui.main.MainActivity;
import com.grab.assignmentgrab.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}

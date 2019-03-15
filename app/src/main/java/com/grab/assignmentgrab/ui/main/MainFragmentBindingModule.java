package com.grab.assignmentgrab.ui.main;

import com.grab.assignmentgrab.ui.details.DetailsFragment;
import com.grab.assignmentgrab.ui.list.ListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}

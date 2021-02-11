package com.pruebacondorlabs.di.module

import com.pruebacondorlabs.view.DetailActivity
import com.pruebacondorlabs.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeDetailActivity(): DetailActivity
}
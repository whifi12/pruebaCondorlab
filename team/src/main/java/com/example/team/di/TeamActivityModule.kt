package com.example.team.di

import com.example.team.view.DetailActivity
import com.example.team.view.IDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TeamActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeDetailActivity(): DetailActivity
}
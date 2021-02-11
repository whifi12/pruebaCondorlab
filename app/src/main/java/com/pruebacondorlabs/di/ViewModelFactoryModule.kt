package com.pruebacondorlabs.di

import androidx.lifecycle.ViewModelProvider
import com.pruebacondorlabs.di.module.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}
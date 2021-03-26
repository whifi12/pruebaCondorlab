package com.pruebacondorlabs.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pruebacondorlabs.di.scope.ViewModelKey
import com.pruebacondorlabs.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(myViewModel: MainViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory) : ViewModelProvider.Factory
}
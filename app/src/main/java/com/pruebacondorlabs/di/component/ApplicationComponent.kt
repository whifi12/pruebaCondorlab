package com.pruebacondorlabs.di.component

import com.pruebacondorlabs.App
import com.pruebacondorlabs.di.module.ActivityModule
import com.pruebacondorlabs.di.module.AppModule
import com.example.domain.di.RepositoryModule
import com.example.domain.di.UseCaseModule
import com.example.team.di.PresenterModel
import com.example.team.di.TeamActivityModule
import com.pruebacondorlabs.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    RepositoryModule::class, UseCaseModule::class,ViewModelModule::class, PresenterModel::class, TeamActivityModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application:App): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: App)
}
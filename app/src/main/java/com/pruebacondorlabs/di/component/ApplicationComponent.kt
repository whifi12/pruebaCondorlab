package com.pruebacondorlabs.di.component

import android.app.Application
import com.pruebacondorlabs.App
import com.pruebacondorlabs.di.module.ActivityModule
import com.pruebacondorlabs.di.module.AppModule
import com.pruebacondorlabs.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    RepositoryModule::class])
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
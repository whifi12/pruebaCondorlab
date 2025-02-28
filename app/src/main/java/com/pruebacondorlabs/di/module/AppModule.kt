package com.pruebacondorlabs.di.module

import android.app.Application
import com.example.domain.di.ServiceModule
import com.example.utilities.base.BaseActivity
import com.example.utilities.util.IValidateInternet
import com.example.utilities.util.ValidateInternet
import com.pruebacondorlabs.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, ServiceModule::class])
class AppModule() {

    @Singleton
    @Provides
    fun providerApplication(app: App) : Application {
        return app;
    }

    @Singleton
    @Provides
    fun providerValidateInternet() : IValidateInternet{
        return ValidateInternet(BaseActivity.context)
    }

}
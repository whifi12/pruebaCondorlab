package com.pruebacondorlabs

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.utilities.base.BaseActivity
import com.pruebacondorlabs.di.component.ApplicationComponent
import com.pruebacondorlabs.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App : Application(),HasActivityInjector {

    @Inject
    lateinit var activityInjector : DispatchingAndroidInjector<Activity>

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().application(this).build()
    }
    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        BaseActivity.context = applicationContext
    }


    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}
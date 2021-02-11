package com.pruebacondorlabs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

open class BaseTest {

    @get:Rule
    open val mockitoRule: MockitoRule = MockitoJUnit.rule()


    @get:Rule
    open var instantRule = InstantTaskExecutorRule()

    companion object { @get:ClassRule
    val schedulers: RxImmediateSchedulerRule = RxImmediateSchedulerRule() }



    @Before
    @Throws(Exception::class)
    open fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
    }


}
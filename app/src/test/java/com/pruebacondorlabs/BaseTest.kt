package com.pruebacondorlabs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
open class BaseTest {


    @get:Rule
    open var instantRule = InstantTaskExecutorRule()



    @get:Rule
    open val testCoroutineRule = TestCoroutineRule()



}
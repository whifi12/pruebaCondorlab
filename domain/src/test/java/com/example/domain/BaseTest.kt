package com.example.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {


    @get:Rule
    open var instantRule = InstantTaskExecutorRule()



    @get:Rule
    open val testCoroutineRule = TestCoroutineRule()



}
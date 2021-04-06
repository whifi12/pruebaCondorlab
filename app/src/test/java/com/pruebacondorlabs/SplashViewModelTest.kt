package com.pruebacondorlabs


import android.os.CountDownTimer
import com.pruebacondorlabs.viewModel.SplashViewModel
import io.mockk.MockKAnnotations
import io.mockk.spyk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SplashViewModelTest {

    lateinit var splashViewModel: SplashViewModel



    @Before
    fun setUp() {
        splashViewModel = spyk(SplashViewModel())
    }

    @Test
    fun validateTimer(){
        splashViewModel.timer()
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                assertTrue(splashViewModel.getTime().value!!)
            }
        }.start()

    }

}
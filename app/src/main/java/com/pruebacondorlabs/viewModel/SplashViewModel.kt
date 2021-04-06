package com.pruebacondorlabs.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val time = MutableLiveData<Boolean>()

    fun timer(){
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                time.value = true
            }
        }.start()
    }

    fun getTime(): MutableLiveData<Boolean>{
        return time
    }

}
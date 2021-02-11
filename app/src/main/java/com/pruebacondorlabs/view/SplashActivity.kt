package com.pruebacondorlabs.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pruebacondorlabs.R
import com.pruebacondorlabs.databinding.ActivitySplashBinding
import com.pruebacondorlabs.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        supportActionBar?.hide()
        listener()
        loadSplash()
    }

    private fun listener(){
        viewModel.getTime().observe(this, Observer<Boolean>{ time ->
            if (time) {
                goToPrincipalActivity()
            }
        })
    }

    private fun loadSplash(){
        viewModel.timer()
    }

    private fun goToPrincipalActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
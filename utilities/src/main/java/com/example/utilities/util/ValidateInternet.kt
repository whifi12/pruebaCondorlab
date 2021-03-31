package com.example.utilities.util

import android.content.Context
import android.net.ConnectivityManager

class ValidateInternet(private val context: Context) : IValidateInternet {
    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting && networkInfo.isAvailable && networkInfo.isConnected
    }

}
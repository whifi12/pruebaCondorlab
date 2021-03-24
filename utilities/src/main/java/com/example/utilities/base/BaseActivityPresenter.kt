package com.example.utilities.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.utilities.base.view_base.IBaseView

open class BaseActivityPresenter<T> : AppCompatActivity(), IBaseView {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
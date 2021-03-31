package com.example.utilities.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.utilities.base.view_base.IBaseView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseActivity : IBaseView, AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var progressDialog: ProgressDialog? = null

    fun configureDagger(){
        AndroidInjection.inject(this)
    }

    companion object {
        lateinit var context : Context
    }


    open fun createProgressDialog() {
        this.progressDialog = ProgressDialog(this)
        this.progressDialog!!.setCancelable(false)
    }

    override fun showProgressDIalog(text: Int) {
        runOnUiThread {
            try {
                progressDialog!!.setMessage(resources.getString(text))
                progressDialog!!.show()
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }

    override fun dismissProgressDialog() {
        this.progressDialog!!.dismiss()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
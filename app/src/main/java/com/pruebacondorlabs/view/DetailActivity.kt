package com.pruebacondorlabs.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.pruebacondorlabs.R
import com.pruebacondorlabs.base.BaseActivity
import com.pruebacondorlabs.databinding.ActivityDetailBinding
import com.pruebacondorlabs.models.Teams
import com.pruebacondorlabs.util.Constants.TEAMS
import com.pruebacondorlabs.viewModel.DetailViewModel


class DetailActivity : BaseActivity() {

    lateinit var binding : ActivityDetailBinding
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadView()
        loadData()
    }

    private fun loadData() {
        val teams = intent.getSerializableExtra(TEAMS) as? Teams
        supportActionBar?.title = teams?.name ?: ""
        viewModel.loadData(teams)
        binding.facebook.setOnClickListener {
            goToNavigator(teams?.facebook ?: "")
        }
        binding.instagram.setOnClickListener {
            goToNavigator(teams?.instagram ?: "")
        }
        binding.web.setOnClickListener {
            goToNavigator(teams?.website ?: "")
        }
        binding.twitter.setOnClickListener {
            goToNavigator(teams?.twitter ?: "")
        }
    }

    private fun goToNavigator(url :String){
        val defaultBrowser =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(url)
        startActivity(defaultBrowser)
    }


    private fun loadView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        configureDagger()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
    }
}
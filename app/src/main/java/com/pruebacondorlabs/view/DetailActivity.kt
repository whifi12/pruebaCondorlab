package com.pruebacondorlabs.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.pruebacondorlabs.R
import com.pruebacondorlabs.adapter.EventRecyclerAdapter
import com.example.utilities.base.BaseActivity
import com.pruebacondorlabs.databinding.ActivityDetailBinding
import com.example.domain.model.Match
import com.example.domain.model.Teams
import com.example.utilities.util.Constants.TEAMS
import com.pruebacondorlabs.viewModel.DetailViewModel


class DetailActivity : BaseActivity() {

    lateinit var binding : ActivityDetailBinding
    lateinit var viewModel: DetailViewModel
    lateinit var adapter : EventRecyclerAdapter
    private var events = ArrayList<Match>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadView()
        listenerObservable()
        loadRecycler()
        loadData()

    }

    private fun loadRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewMatchs.layoutManager = linearLayoutManager
        adapter = EventRecyclerAdapter(events)
        binding.recyclerViewMatchs.adapter = adapter
    }

    private fun listenerObservable(){
        viewModel.getEvents().observe(this) { events ->
            adapter.setItems(events)
        }
        viewModel.progress().observe(this){ progress ->
            if(progress){
                showProgressDIalog(R.string.wait)
            }else{
                dismissProgressDialog()
            }
        }
    }

    private fun loadData() {
        val teams = intent.getSerializableExtra(TEAMS) as? Teams
        supportActionBar?.title = teams?.name ?: ""
        viewModel.loadData(teams)
        teams?.let { listener(it) }
    }

    private fun listener(teams:Teams){
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
package com.pruebacondorlabs.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.pruebacondorlabs.R
import com.pruebacondorlabs.adapter.TeamsRecyclerAdapter
import com.pruebacondorlabs.base.BaseActivity
import com.pruebacondorlabs.databinding.ActivityMainBinding
import com.pruebacondorlabs.models.Teams
import com.pruebacondorlabs.util.Constants.SPAIN
import com.pruebacondorlabs.viewModel.MainViewModel

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter : TeamsRecyclerAdapter
    private val teams = ArrayList<Teams>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadView()
        loadRecycler()
        listenerObservable()
        loadService()
    }

    private fun loadRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewTeams.layoutManager = linearLayoutManager
        adapter = TeamsRecyclerAdapter(this, teams)
        binding.recyclerViewTeams.adapter = adapter
    }

    private fun loadView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        configureDagger()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        binding.alertasviewmodel = viewModel
        supportActionBar?.setTitle(R.string.main)
    }


    private fun listenerObservable(){
        viewModel.teams().observe(this) { teams ->
            adapter.setItems(teams)
        }
    }

    private fun loadService(){
        viewModel.getTeams(SPAIN)
    }

}
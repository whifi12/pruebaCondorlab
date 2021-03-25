package com.pruebacondorlabs.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.pruebacondorlabs.R
import com.pruebacondorlabs.adapter.TeamsRecyclerAdapter
import com.example.utilities.base.BaseActivity
import com.pruebacondorlabs.databinding.ActivityMainBinding
import com.example.domain.model.response.Teams
import com.example.utilities.util.Constants.SPAIN
import com.example.utilities.util.Constants.TEAMS
import com.pruebacondorlabs.viewModel.MainViewModel

class MainActivity : BaseActivity(), TeamsRecyclerAdapter.OnUsersListener {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter : TeamsRecyclerAdapter
    private val teams = ArrayList<Teams>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadView()
        loadRecycler()
        listenerObservable()
        createProgressDialog()
        loadService()
    }

    private fun loadRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewTeams.layoutManager = linearLayoutManager
        adapter = TeamsRecyclerAdapter(teams,this)
        binding.recyclerViewTeams.adapter = adapter
    }

    private fun loadView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        configureDagger()
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        supportActionBar?.setTitle(R.string.main)
    }


    private fun listenerObservable(){
        viewModel.teams().observe(this) { teams ->
            adapter.setItems(teams)
        }
        viewModel.progress().observe(this){ progress ->
            if(progress){
                showProgressDIalog(R.string.wait)
            }else{
                dismissProgressDialog()
            }
        }
    }

    private fun loadService(){
        viewModel.loadTeams(SPAIN)
    }

    override fun onItemClick(teams: Teams) {
        goToDetailTeam(teams)
    }

    private fun goToDetailTeam(teams: Teams){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(TEAMS, teams)
        startActivity(intent)
    }

}
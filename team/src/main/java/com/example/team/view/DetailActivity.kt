package com.example.team.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.team.R
import com.example.team.adapter.EventRecyclerAdapter
import com.example.team.databinding.ActivityDetailBinding
import com.example.team.presenter.DetailPresenter
import com.example.utilities.base.BaseActivity
import com.example.utilities.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailActivity : BaseActivity(), IDetailActivity {

    lateinit var binding: ActivityDetailBinding
    lateinit var adapter: EventRecyclerAdapter

    @Inject
    lateinit var presenter: DetailPresenter

    private var events = ArrayList<Match>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadView()
        createProgressDialog()
        configureDagger()
        loadRecycler()
        loadService()
    }

    private fun loadRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewMatchs.layoutManager = linearLayoutManager
        adapter = EventRecyclerAdapter(events)
        binding.recyclerViewMatchs.adapter = adapter
    }

    private fun loadView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

    }

    private fun loadService() {
        val teams = intent.getSerializableExtra(Constants.TEAMS) as? Teams
        presenter.injectView(this)
        supportActionBar?.title = teams?.name ?: ""
        lifecycleScope.launch {
            presenter.loadData(teams)
        }
        teams?.let { listener(it) }
    }

    private fun listener(teams: Teams) {
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

    private fun goToNavigator(url: String) {
        val defaultBrowser =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(url)
        startActivity(defaultBrowser)
    }

    override fun loadEvents(events: List<Match>) {
        adapter.setItems(events)
    }

    override fun loadData(teams: Teams) {
        validateData(teams)
        loadInfo(teams)
    }

    override fun loadError(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG)
    }

    fun loadInfo(teams: Teams) {
        binding.name.text = teams.name
        binding.description.text = teams.description
        binding.year.text = teams.year
    }


    fun validateData(teams: Teams) {
        if (teams.jersey != null && teams.jersey!!.isNotEmpty()) {
            binding.jersey.visibility = View.VISIBLE
            binding.contact.visibility = View.VISIBLE
            Picasso.get().load(teams.jersey).into(binding.jersey)
        }
        if (teams.facebook.isNotEmpty()) {
            binding.facebook.visibility = View.VISIBLE
            binding.contact.visibility = View.VISIBLE
            binding.facebook.text = teams.facebook
        }
        if (teams.instagram.isNotEmpty()) {
            binding.facebook.visibility = View.VISIBLE
            binding.contact.visibility = View.VISIBLE
            binding.instagram.text = teams.instagram
        }
        if (teams.twitter.isNotEmpty()) {
            binding.twitter.visibility = View.VISIBLE
            binding.contact.visibility = View.VISIBLE
            binding.twitter.text = teams.twitter
        }
        if (teams.website.isNotEmpty()) {
            binding.web.visibility = View.VISIBLE
            binding.contact.visibility = View.VISIBLE
            binding.web.text = teams.website
        }
        if (teams.badge!!.isNotEmpty()){
            Picasso.get().load(teams.badge).into(binding.logo)
        }
    }
}
package com.pruebacondorlabs.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebacondorlabs.R
import com.pruebacondorlabs.bussinessLogic.ILeaguesBL
import com.pruebacondorlabs.models.Teams
import com.pruebacondorlabs.repositories.LeaguesRepository
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    private val leaguesBL: ILeaguesBL = leaguesRepository
    private val teams = MutableLiveData<List<Teams>>()
    private val disposables = CompositeDisposable();
    var name = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var year = MutableLiveData<String>()
    var badge = MutableLiveData<String>()
    var jersey = MutableLiveData<String>()
    var visibilityJersey = MutableLiveData<Int>()
    var web = MutableLiveData<String>()
    var facebook = MutableLiveData<String>()
    var twitter = MutableLiveData<String>()
    var instagram = MutableLiveData<String>()
    var visibilityFacebook = MutableLiveData<Int>()
    var visibilityWeb = MutableLiveData<Int>()
    var visibilityTwitter = MutableLiveData<Int>()
    var visibilityInstagram = MutableLiveData<Int>()


    fun loadData(teams: Teams?){
        if (teams != null) {
            name.value = teams.name
            description.value = teams.description
            year.value = teams.year
            badge.value = teams.badge
            validateData(teams)
        }
    }

    private fun validateData(teams: Teams){
        if(teams.jersey != null){
            visibilityJersey.value = View.VISIBLE
            jersey.value = teams.jersey
        }
        if(teams.facebook.isNotEmpty()){
            visibilityFacebook.value = View.VISIBLE
            facebook.value = teams.facebook
        }
        if(teams.instagram.isNotEmpty()){
            visibilityInstagram.value = View.VISIBLE
            instagram.value = teams.instagram
        }
        if(teams.twitter.isNotEmpty()){
            visibilityTwitter.value = View.VISIBLE
            twitter.value = teams.twitter
        }
        if(teams.website.isNotEmpty()){
            visibilityWeb.value = View.VISIBLE
            web.value = teams.website
        }
    }


}
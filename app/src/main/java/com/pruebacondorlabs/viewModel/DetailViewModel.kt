package com.pruebacondorlabs.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetEventsUseCase
import com.example.utilities.util.EmptyObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class DetailViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val events = MutableLiveData<List<Match>>()
    private val disposables = CompositeDisposable();
    private val progress = MutableLiveData<Boolean>()
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
    var visibilityContact = MutableLiveData<Int>()
    var visibilityTwitter = MutableLiveData<Int>()
    var visibilityInstagram = MutableLiveData<Int>()


    open fun loadData(teams: Teams?){
        if (teams != null) {
            getLastEvents(teams.idTeam)
            name.value = teams.name
            description.value = teams.description
            year.value = teams.year
            badge.value = teams.badge
            visibilityData()
            validateData(teams)
        }
    }

    open fun getLastEvents(id: String) {
        progress.value = true
        getEventsUseCase.execute(observer = TeamsEventObserver(),params = id)
    }

    open inner class TeamsEventObserver :  EmptyObserver<Events>() {
        override fun onNext(result: Events) {
            loadDataEvents(events = result)
        }

        override fun onError(error: Throwable) {
            errorEvents(error)
        }

        override fun onComplete() {
            progress.value = false
        }
    }

    open fun errorEvents(error: Throwable) {
        error.message?.let { Log.e(error.localizedMessage, it) }
    }


    open fun loadDataEvents(events: Events?) {
        if (events != null) {
            this.events.value = events.events
        }

    }

    open fun visibilityData(){
        visibilityJersey.value = View.GONE
        visibilityContact.value = View.GONE
        visibilityFacebook.value = View.GONE
        visibilityInstagram.value = View.GONE
        visibilityWeb.value = View.GONE
        visibilityTwitter.value = View.GONE
    }

    open fun validateData(teams: Teams){
        if(teams.jersey != null){
            visibilityJersey.value = View.VISIBLE
            visibilityContact.value = View.VISIBLE
            jersey.value = teams.jersey
        }
        if(teams.facebook.isNotEmpty()){
            visibilityFacebook.value = View.VISIBLE
            visibilityContact.value = View.VISIBLE
            facebook.value = teams.facebook
        }
        if(teams.instagram.isNotEmpty()){
            visibilityInstagram.value = View.VISIBLE
            visibilityContact.value = View.VISIBLE
            instagram.value = teams.instagram
        }
        if(teams.twitter.isNotEmpty()){
            visibilityTwitter.value = View.VISIBLE
            visibilityContact.value = View.VISIBLE
            twitter.value = teams.twitter
        }
        if(teams.website.isNotEmpty()){
            visibilityWeb.value = View.VISIBLE
            visibilityContact.value = View.VISIBLE
            web.value = teams.website
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun getEvents() : MutableLiveData<List<Match>> {
        return events
    }

    fun progress(): LiveData<Boolean> {
        return progress
    }


}
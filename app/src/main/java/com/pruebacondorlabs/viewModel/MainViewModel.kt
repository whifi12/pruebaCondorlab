package com.pruebacondorlabs.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetTeamsUseCase
import com.example.utilities.util.Constants.SPORT
import com.example.utilities.util.EmptyObserver
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {

    private val teamUseCase = getTeamsUseCase
    private val teams = MutableLiveData<List<Teams>>()
    private val progress = MutableLiveData<Boolean>()

    fun loadTeams(country: String){
        progress.value = true
        getTeams(country)
    }

    fun getTeams(country: String) {
        val teamRequest = TeamRequest(SPORT,country)
        teamUseCase.execute(observer = TeamsEventObserver(), params = teamRequest)
    }

    open inner class TeamsEventObserver :  EmptyObserver<League>() {
        override fun onNext(result: League) {
           loadData(league = result)
        }

        override fun onError(error: Throwable) {
           errorTeams(error)
        }

        override fun onComplete() {
            progress.value = false
        }
    }

    open fun errorTeams(error: Throwable?){
        if (error != null) {
            error.message?.let { Log.e(error.localizedMessage, it) }
        }

    }

    open fun loadData(league: League?) {
        if (league != null) {
            teams.value = league.teams
        }
    }

    fun progress(): LiveData<Boolean> {
        return progress
    }

    fun teams() : LiveData<List<Teams>> {
        return teams
    }


}
package com.pruebacondorlabs.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.League
import com.example.domain.model.Teams
import com.example.domain.usecase.IGetTeamUseCase
import com.example.utilities.util.Constants.SPORT
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    getTeamsUseCase: IGetTeamUseCase
) : ViewModel() {

    private val teamUseCase = getTeamsUseCase
    private val teams = MutableLiveData<List<Teams>>()
    private val disposables = CompositeDisposable();
    private val progress = MutableLiveData<Boolean>()

    fun loadTeams(country: String){
        progress.value = true
        getTeams(country)
    }

    fun getTeams(country: String) {
        val disposable = teamUseCase.getTeams(SPORT, country)
            .subscribe({ league: League? ->
                loadData(league)
            }, { error: Throwable? ->
                errorTeams(error)
            })
        disposables.add(disposable)
    }

    open fun errorTeams(error: Throwable?){
        if (error != null) {
            error.message?.let { Log.e(error.localizedMessage, it) }
        }
        progress.value = false
    }

    open fun loadData(league: League?) {
        if (league != null) {
            teams.value = league.teams
        }
        progress.value = false
    }

    fun progress(): LiveData<Boolean> {
        return progress
    }

    fun teams() : LiveData<List<Teams>> {
        return teams
    }


}
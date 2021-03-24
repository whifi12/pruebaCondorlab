package com.pruebacondorlabs.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebacondorlabs.bussinessLogic.ILeaguesBL
import com.pruebacondorlabs.models.League
import com.pruebacondorlabs.models.Teams
import com.pruebacondorlabs.repositories.LeaguesRepository
import com.example.utilities.util.Constants.SPORT
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    private val leaguesBL: ILeaguesBL = leaguesRepository
    private val teams = MutableLiveData<List<Teams>>()
    private val disposables = CompositeDisposable();
    private val progress = MutableLiveData<Boolean>()

    fun getTeams(country: String) {
        progress.value = true
        val disposable = leaguesBL.getTeams(SPORT, country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ league: League? ->
                loadData(league)
                progress.value = false
            }, { error: Throwable? ->
                if (error != null) {
                    error.message?.let { Log.e(error.localizedMessage, it) }
                }
                progress.value = false
            })
        disposables.add(disposable)
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
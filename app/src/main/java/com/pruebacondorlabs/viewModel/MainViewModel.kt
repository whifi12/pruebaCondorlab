package com.pruebacondorlabs.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.GetTeamsUseCase
import com.example.utilities.util.Constants.SPORT
import com.example.utilities.util.EmptyObserver
import com.example.utilities.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
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
        viewModelScope.launch {
            val teamRequest = TeamRequest(SPORT,country)
            val data = teamUseCase.execute(teamRequest)
            responseTeams(data)
            progress.value = false
        }

    }

    fun responseTeams(result :Response<League>){
        if(result.isSuccessful){
            loadData(result.body())
        }else{
            Log.e(result.code().toString(),result.message())
        }
    }


    fun loadData(league: League?) {
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
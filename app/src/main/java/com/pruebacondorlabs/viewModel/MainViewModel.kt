package com.pruebacondorlabs.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetConfigUseCase
import com.example.domain.usecase.GetTeamsUseCase
import com.example.utilities.util.Constants.SPORT
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val getConfigUseCase: GetConfigUseCase
) : ViewModel() {

    private val _teams = MutableLiveData<List<Teams>>()
    private val _progress = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()
    private val _config = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> get() = _progress
    val teams: LiveData<List<Teams>> get() = _teams
    val error : LiveData<String> get() = _error
    val config : LiveData<Boolean> get() = _config

    fun loadConfig(){
        val config = getConfigUseCase.execute(Any())
        _config.value = config
    }

    fun loadTeams(country: String){
        _progress.value = true
        getTeams(country)
    }

    fun getTeams(country: String) {
        viewModelScope.launch {
            val teamRequest = TeamRequest(SPORT,country)
            val data = getTeamsUseCase.execute(teamRequest)
            responseTeams(data)
            _progress.value = false
        }

    }

    fun responseTeams(result :Response<League>){
        if(result.isSuccessful){
            loadData(result.body())
        }else{
             _error.value =  result.message()
        }
    }


    fun loadData(league: League?) {
        if (league?.teams != null) {
            _teams.value = league.teams
        }
    }



}
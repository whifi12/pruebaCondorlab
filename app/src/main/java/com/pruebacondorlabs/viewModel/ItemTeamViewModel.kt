package com.pruebacondorlabs.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.response.Teams

class ItemTeamViewModel() {

    private var _name = MutableLiveData<String>()
    private var _stadium = MutableLiveData<String>()
    private var _imageUrl = MutableLiveData<String>()

    val name : LiveData<String> get() = _name
    val stadium : LiveData<String> get() = _stadium
    val imageUrl : LiveData<String> get() = _imageUrl


     fun teams(teams: Teams){
        _name.value = teams.name
        _stadium.value = teams.stadium
        _imageUrl.value = teams.badge
    }

}
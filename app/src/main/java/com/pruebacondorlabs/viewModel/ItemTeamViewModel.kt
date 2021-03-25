package com.pruebacondorlabs.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.response.Teams

class ItemTeamViewModel() {

    var name = MutableLiveData<String>()
    var stadium = MutableLiveData<String>()
    var imageUrl = MutableLiveData<String>()


     fun teams(teams: Teams){
        name.value = teams.name
        stadium.value = teams.stadium
        imageUrl.value = teams.badge
    }

}
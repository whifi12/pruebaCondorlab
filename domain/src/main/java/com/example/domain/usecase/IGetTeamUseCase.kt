package com.example.domain.usecase

import com.example.domain.model.League
import io.reactivex.Observable

interface IGetTeamUseCase {

    fun getTeams(sport : String, country : String) : Observable<League>

}
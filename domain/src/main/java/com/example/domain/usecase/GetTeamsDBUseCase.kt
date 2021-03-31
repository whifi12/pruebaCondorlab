package com.example.domain.usecase

import com.example.domain.model.response.League
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.IteratorGeneral
import javax.inject.Inject

class GetTeamsDBUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy) : IteratorGeneral<League,Any> {

    override fun execute(params: Any): League {
        return leaguesRepository.getTeams()
    }


}
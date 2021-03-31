package com.example.domain.usecase

import com.example.domain.model.response.Teams
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.IteratorGeneral
import javax.inject.Inject

class SaveTeamsUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy) : IteratorGeneral<Any,List<Teams>> {


    override fun execute(params: List<Teams>): Any {
        leaguesRepository.saveDataTeams(params)
        return Any()
    }


}
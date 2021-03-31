package com.example.domain.usecase

import com.example.domain.model.response.Events
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.IteratorGeneral
import javax.inject.Inject

class GetEventsDBUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy) : IteratorGeneral<Events,String> {


    override fun execute(params: String): Events {
       return leaguesRepository.getEventsDB(params)
    }

}
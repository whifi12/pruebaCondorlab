package com.example.domain.usecase

import com.example.domain.model.request.EventRequest
import com.example.domain.model.response.Teams
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.IteratorGeneral
import javax.inject.Inject

class SaveEventsUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy
) : IteratorGeneral<Any, EventRequest> {

    override fun execute(params: EventRequest): Any {
        leaguesRepository.saveEvents(params)
        return Any()
    }
}
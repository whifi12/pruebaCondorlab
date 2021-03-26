package com.example.domain.usecase

import com.example.domain.model.response.Events
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.Iterator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy,
    private val dispatcher: CoroutineDispatcher
)  : Iterator<Response<Events>,String> {


    override suspend fun execute(params: String): Response<Events> {
        return withContext(dispatcher) {
           leaguesRepository.getEvents(params)
        }
    }
}
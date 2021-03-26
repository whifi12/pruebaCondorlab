package com.example.domain.usecase

import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.Iterator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy,
    private val dispatcher: CoroutineDispatcher
) : Iterator<Response<League>,TeamRequest> {


    override suspend fun execute(params: TeamRequest): Response<League> {
        return withContext(dispatcher) {
            leaguesRepository.getTeams(params)
        }
    }

}
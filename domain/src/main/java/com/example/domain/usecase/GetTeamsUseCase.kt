package com.example.domain.usecase

import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.repository.ILeaguesRepositoy
import com.example.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    threadExecutor: Scheduler,
    postExecutionThread: Scheduler,
    private val leaguesRepository: ILeaguesRepositoy
) : ObservableUseCase<League, TeamRequest>(threadExecutor, postExecutionThread){


    override fun buildUseCaseObservable(params: TeamRequest?): Observable<League> {
        return leaguesRepository.getTeams(params!!)
    }

}
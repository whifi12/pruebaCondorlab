package com.example.domain.usecase

import com.example.domain.model.League
import com.example.domain.repository.ILeaguesRepositoy
import com.example.utilities.base.BaseReactiveUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepositoy) :IGetTeamUseCase {


    override fun getTeams(sport : String, country : String) : Observable<League>{
        return leaguesRepository.getTeams(sport,country).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
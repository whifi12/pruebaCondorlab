package com.example.team.di

import com.example.domain.usecase.GetEventsDBUseCase
import com.example.domain.usecase.GetEventsUseCase
import com.example.domain.usecase.SaveEventsUseCase
import com.example.team.presenter.DetailPresenter
import com.example.team.view.IDetailActivity
import com.example.utilities.util.ValidateInternet
import dagger.Module
import javax.inject.Inject

@Module
class PresenterModel {

    @Inject
    fun providerDetailPresenter(getEventsUseCase: GetEventsUseCase,
                                getEventsDB : GetEventsDBUseCase,
                                saveEventsUseCase: SaveEventsUseCase,
                                validateInternet: ValidateInternet) : DetailPresenter {
        return DetailPresenter(getEventsUseCase,getEventsDB,saveEventsUseCase,validateInternet)
    }

}
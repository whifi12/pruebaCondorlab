package com.example.team.di

import com.example.domain.usecase.GetEventsUseCase
import com.example.team.presenter.DetailPresenter
import com.example.team.view.IDetailActivity
import dagger.Module
import javax.inject.Inject

@Module
class PresenterModel {

    @Inject
    fun providerDetailPresenter(getEventsUseCase: GetEventsUseCase) : DetailPresenter {
        return DetailPresenter(getEventsUseCase)
    }

}
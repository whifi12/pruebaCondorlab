package com.example.team.presenter

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.response.Events
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetEventsUseCase
import com.example.team.view.IDetailActivity
import com.example.utilities.base.BasePresenter
import com.example.utilities.util.EmptyObserver
import javax.inject.Inject

class DetailPresenter  @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : BasePresenter<IDetailActivity>() {

    lateinit var  view : IDetailActivity
    private val progress = MutableLiveData<Boolean>()

    open fun injectView(view: IDetailActivity){
        this.view = view
    }


    open fun loadData(teams: Teams?){
        if (teams != null) {
            view.loadData(teams)
            getLastEvents(teams.idTeam)
        }
    }

    open fun getLastEvents(id: String) {
        progress.value = true
        getEventsUseCase.execute(observer = TeamsEventObserver(),params = id)
    }

    open inner class TeamsEventObserver :  EmptyObserver<Events>() {
        override fun onNext(result: Events) {
            loadDataEvents(events = result)
        }

        override fun onError(error: Throwable) {
            errorEvents(error)
        }

        override fun onComplete() {
            progress.value = false
        }
    }

    open fun errorEvents(error: Throwable) {
        error.message?.let { Log.e(error.localizedMessage, it) }
    }


    open fun loadDataEvents(events: Events?) {
        if (events != null) {
           view.loadEvents(events = events.events)
        }

    }




}
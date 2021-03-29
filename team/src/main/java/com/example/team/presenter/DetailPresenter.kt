package com.example.team.presenter

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetEventsUseCase
import com.example.team.R
import com.example.team.view.IDetailActivity
import com.example.utilities.base.BasePresenter
import com.example.utilities.util.EmptyObserver
import retrofit2.Response
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : BasePresenter<IDetailActivity>() {

    lateinit var view: IDetailActivity

    fun injectView(view: IDetailActivity) {
        this.view = view
    }


    suspend fun loadData(teams: Teams?) {
        if (teams != null) {
            view.loadData(teams)
            getLastEvents(teams.idTeam)
        }
    }

    suspend fun getLastEvents(id: String) {
        view.showProgressDIalog(R.string.wait)
        response(getEventsUseCase.execute(id))
    }

    fun response(result: Response<Events>) {
        view.dismissProgressDialog()
        if (result.isSuccessful) {
            loadDataEvents(result.body())
        } else {
            view.loadError(result.message())
        }
    }


    fun loadDataEvents(events: Events?) {
        if (events != null) {
            view.loadEvents(events = events.events)
        }

    }

}
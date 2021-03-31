package com.example.team.presenter

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.request.EventRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetEventsDBUseCase
import com.example.domain.usecase.GetEventsUseCase
import com.example.domain.usecase.SaveEventsUseCase
import com.example.team.R
import com.example.team.view.IDetailActivity
import com.example.utilities.base.BasePresenter
import com.example.utilities.util.EmptyObserver
import com.example.utilities.util.IValidateInternet
import retrofit2.Response
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val getEventsDB : GetEventsDBUseCase,
    private val saveEventsUseCase: SaveEventsUseCase,
    private val validateInternet: IValidateInternet
) : BasePresenter<IDetailActivity>() {

    lateinit var view: IDetailActivity

    fun injectView(view: IDetailActivity) {
        this.view = view
    }


    suspend fun loadData(teams: Teams?) {
        if (teams != null) {
            view.loadData(teams)
            validateDataSource(teams.idTeam)
        }
    }

    suspend fun validateDataSource(id : String){
        val matches = getEventsDB.execute(id)
        if (matches.events.isEmpty()){
            getLastEvents(id)
        }else{
            loadDataEvents(events = matches, id = id)
        }
    }

    suspend fun getLastEvents(id: String) {
        if (validateInternet.isConnected()){
            view.showProgressDIalog(R.string.wait)
            response(getEventsUseCase.execute(id),id)
        }
    }

    fun response(result: Response<Events>,id : String) {
        view.dismissProgressDialog()
        if (result.isSuccessful) {
            loadDataEvents(result.body(),id)
        } else {
            view.loadError(result.message() ?: "error")
        }
    }


    fun loadDataEvents(events: Events?, id: String) {
        if (events != null) {
            saveEventsUseCase.execute(EventRequest(id,events.events))
            view.loadEvents(events = events.events)
        }

    }

}
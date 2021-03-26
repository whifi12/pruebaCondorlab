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
import com.example.team.view.IDetailActivity
import com.example.utilities.base.BasePresenter
import com.example.utilities.util.EmptyObserver
import retrofit2.Response
import javax.inject.Inject

class DetailPresenter  @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : BasePresenter<IDetailActivity>() {

    lateinit var  view : IDetailActivity
    private val progress = MutableLiveData<Boolean>()

    open fun injectView(view: IDetailActivity){
        this.view = view
    }


    open suspend fun loadData(teams: Teams?){
        if (teams != null) {
            view.loadData(teams)
            getLastEvents(teams.idTeam)
        }
    }

    open suspend fun getLastEvents(id: String) {
        progress.value = true
        response(getEventsUseCase.execute(id))
    }

    open fun response(result : Response<Events>){
        if(result.isSuccessful){
            loadDataEvents(result.body())
        }else{
            Log.e(result.code().toString(),result.message())
        }
    }


    open fun loadDataEvents(events: Events?) {
        if (events != null) {
           view.loadEvents(events = events.events)
        }

    }



}
package com.example.team.view

import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.utilities.base.view_base.IBaseView

interface IDetailActivity : IBaseView {

     fun loadEvents(events: List<Match>)

     fun loadData(teams: Teams)

}
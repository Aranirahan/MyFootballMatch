package com.aranirahan.myfootballapi.presenter

import com.aranirahan.myfootballapi.model.item.MatchEventResponse
import com.aranirahan.myfootballapi.view.myInterface.DetailView
import com.aranirahan.myfootballapi.model.api.ApiRepository
import com.aranirahan.myfootballapi.model.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {

    fun geDetailTeamList(teamA: String?, teamB: String?) {
        view.showLoading()

        doAsync {
            val dataA = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(teamA)),
                    MatchEventResponse::class.java
            )
            val dataB = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(teamB)),
                    MatchEventResponse::class.java
            )


            uiThread {
                view.hideLoading()
                view.showTeamsList(dataA.teams, dataB.teams)
            }
        }
    }
}
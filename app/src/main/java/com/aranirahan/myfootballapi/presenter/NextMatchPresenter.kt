package com.aranirahan.myfootballapi.presenter

import com.aranirahan.myfootballapi.model.api.TheSportDBApi
import com.aranirahan.myfootballapi.model.api.ApiRepository
import com.aranirahan.myfootballapi.model.item.MatchEventResponse
import com.aranirahan.myfootballapi.view.myInterface.TeamsView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: TeamsView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getMatchList(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(match)),
                    MatchEventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchEventList(data.events)
            }
        }
    }
}
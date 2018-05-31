package com.aranirahan.myfootballapi.presenter

import com.aranirahan.myfootballapi.model.TheSportDBApi
import com.aranirahan.myfootballapi.model.ApiRepository
import com.aranirahan.myfootballapi.model.MatchEventResponse
import com.aranirahan.myfootballapi.view.myInterface.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getMatchList(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPastMatchEvent(match)),
                    MatchEventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchEventList(data.events)
            }
        }
    }
}
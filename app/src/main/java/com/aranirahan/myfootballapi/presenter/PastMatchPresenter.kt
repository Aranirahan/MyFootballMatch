package com.aranirahan.myfootballapi.presenter

import com.aranirahan.myfootballapi.model.api.TheSportDBApi
import com.aranirahan.myfootballapi.model.api.ApiRepository
import com.aranirahan.myfootballapi.model.item.MatchEventResponse
import com.aranirahan.myfootballapi.view.myInterface.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PastMatchPresenter(private val view: TeamsView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getMatchList(match: String?) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPastMatchEvent(match)),
                        MatchEventResponse::class.java
                )
            }
            view.showMatchEventList(data.await().events)
            view.hideLoading()
        }
    }
}

package com.aranirahan.myfootballapi.match

import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.model.MatchEventResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: MatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getMatchList(match: String?) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextMatchEvent(match)),
                        MatchEventResponse::class.java
                )
            }
            view.showMatchEventList(data.await().events)
            view.hideLoading()
        }
    }
}

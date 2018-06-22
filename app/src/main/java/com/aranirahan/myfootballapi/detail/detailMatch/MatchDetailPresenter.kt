package com.aranirahan.myfootballapi.detail.detailMatch

import com.aranirahan.myfootballapi.model.MatchEventResponse
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.match.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailPresenter(private val view: MatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getDetailMatch(event: String?) {
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getDetailMatchEvent(event)),
                        MatchEventResponse::class.java
                )
            }
            view.showMatchEventList(data.await().events)
            view.hideLoading()
        }
    }
}
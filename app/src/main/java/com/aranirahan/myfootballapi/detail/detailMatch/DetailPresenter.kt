package com.aranirahan.myfootballapi.detail.detailMatch

import com.aranirahan.myfootballapi.model.MatchEventResponse
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {

    fun geDetailTeamList(teamA: String?, teamB: String?) {
        view.showLoading()

        async(UI) {
            val dataA = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(teamA)),
                        MatchEventResponse::class.java
                )
            }
            val dataB = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(teamB)),
                        MatchEventResponse::class.java
                )
            }
            view.showTeamsList(dataA.await().teams, dataB.await().teams)
            view.hideLoading()
        }
    }
}

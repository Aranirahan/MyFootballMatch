package com.aranirahan.myfootballapi.team

import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.model.TeamResponse
import com.aranirahan.myfootballapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?, leagueSearch: String?) {
        view.showLoading()
        if (leagueSearch == "eror") {
            async(context.main) {
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getTeams(league)),
                            TeamResponse::class.java
                    )
                }
                view.showTeamList(data.await().teams)
                view.hideLoading()
            }
        } else if (league == "eror") {
            async(context.main) {
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getTeamSearch(leagueSearch)),
                            TeamResponse::class.java
                    )
                }
                view.showTeamList(data.await().teams)
                view.hideLoading()
            }
        }
    }
}
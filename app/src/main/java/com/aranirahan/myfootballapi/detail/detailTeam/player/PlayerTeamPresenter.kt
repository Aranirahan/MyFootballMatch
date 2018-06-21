package com.aranirahan.myfootballapi.detail.detailTeam.player

import android.util.Log
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.model.PlayerTeamResponse
import com.aranirahan.myfootballapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerTeamPresenter(private val view: PlayerTeamView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(idTeam: String?) {
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerTeam(idTeam)),
                        PlayerTeamResponse::class.java
                )
            }
            view.showPlayerList(data.await().playerTeams)
            Log.d("myPTP", data.await().playerTeams.toString())
            view.hideLoading()
        }
    }
}
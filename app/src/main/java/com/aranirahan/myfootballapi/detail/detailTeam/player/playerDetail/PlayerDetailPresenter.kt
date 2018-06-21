package com.aranirahan.myfootballapi.detail.detailTeam.player.playerDetail

import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.model.PlayerDetailResponse
import com.aranirahan.myfootballapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailPresenter(private val view: PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(idTeam: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerDetail(idTeam)),
                        PlayerDetailResponse::class.java
                )
            }
            view.showPlayerDetail(data.await().playerDetails)
            view.hideLoading()
        }
    }
}
package com.aranirahan.myfootballapi.match

import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.model.MatchEventResponse
import com.aranirahan.myfootballapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PastMatchPresenter(private val view: MatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchList(match: String?, matchSearch: String?) {
        view.showLoading()

        async(context.main) {
            if (matchSearch == "empty_parameter") {
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getPastMatchEvent(match)),
                            MatchEventResponse::class.java
                    )
                }
                view.showMatchEventList(data.await().events)
                view.hideLoading()
            } else if (match == "empty_parameter") {
                val dataSearch = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getEventSearch(matchSearch)),
                            MatchEventResponse::class.java
                    )
                }
                view.showMatchEventList(dataSearch.await().event)
                view.hideLoading()
            }
        }
    }
}


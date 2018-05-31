package com.aranirahan.myfootballapi.model

import com.aranirahan.myfootballapi.BuildConfig

object TheSportDBApi {

    fun getPastMatchEvent(pastLeague: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + pastLeague
    }
}
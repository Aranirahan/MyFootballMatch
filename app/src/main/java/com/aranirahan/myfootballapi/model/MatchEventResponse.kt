package com.aranirahan.myfootballapi.model
import com.google.gson.annotations.SerializedName

data class MatchEventResponse(
        @field:SerializedName("events")
        val events: List<MatchEvent>? = null,

        @field:SerializedName("teams")
        val teams: List<Team>? = null
)
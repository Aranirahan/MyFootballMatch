package com.aranirahan.myfootballapi.model
import com.google.gson.annotations.SerializedName

data class MatchEventResponse(
        @field:SerializedName("events")
        val events: List<MatchEvent>? = null
)
package com.aranirahan.myfootballapi.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = null
)
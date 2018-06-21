package com.aranirahan.myfootballapi.model

import com.google.gson.annotations.SerializedName

data class PlayerTeamResponse(

        @field:SerializedName("player")
        val playerTeams: List<PlayerTeam>
)
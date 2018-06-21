package com.aranirahan.myfootballapi.model

import com.google.gson.annotations.SerializedName

data class PlayerDetailResponse(
        @field:SerializedName("players")
        val playerDetails: List<PlayerDetail>)
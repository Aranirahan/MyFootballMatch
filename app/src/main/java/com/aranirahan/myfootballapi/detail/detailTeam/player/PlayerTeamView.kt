package com.aranirahan.myfootballapi.detail.detailTeam.player

import com.aranirahan.myfootballapi.model.PlayerTeam

interface PlayerTeamView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<PlayerTeam>)
}
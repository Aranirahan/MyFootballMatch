package com.aranirahan.myfootballapi.detail.detailTeam.player.playerDetail

import com.aranirahan.myfootballapi.model.PlayerDetail

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<PlayerDetail>)
}
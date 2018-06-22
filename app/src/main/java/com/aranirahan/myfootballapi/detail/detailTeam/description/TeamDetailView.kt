package com.aranirahan.myfootballapi.detail.detailTeam.description

import com.aranirahan.myfootballapi.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}
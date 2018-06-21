package com.aranirahan.myfootballapi.team

import com.aranirahan.myfootballapi.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}
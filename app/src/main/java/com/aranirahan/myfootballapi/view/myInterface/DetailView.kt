package com.aranirahan.myfootballapi.view.myInterface

import com.aranirahan.myfootballapi.model.Team

interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(data: List<Team>?, data2: List<Team>?)
}
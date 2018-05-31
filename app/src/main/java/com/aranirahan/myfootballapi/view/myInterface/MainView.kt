package com.aranirahan.myfootballapi.view.myInterface

import com.aranirahan.myfootballapi.model.MatchEvent

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<MatchEvent>?)
}
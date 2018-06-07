package com.aranirahan.myfootballapi.view.myInterface

import com.aranirahan.myfootballapi.model.item.MatchEvent

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchEventList(data: List<MatchEvent>?)
}
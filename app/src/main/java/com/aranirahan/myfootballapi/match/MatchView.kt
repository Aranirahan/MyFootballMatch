package com.aranirahan.myfootballapi.match

import com.aranirahan.myfootballapi.model.MatchEvent

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchEventList(data: List<MatchEvent>?)
}
package com.aranirahan.myfootballapi.detail.detailTeam

import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.api.TheSportDBApi
import com.aranirahan.myfootballapi.detail.detailTeam.description.TeamDetailPresenter
import com.aranirahan.myfootballapi.detail.detailTeam.description.TeamDetailView
import com.aranirahan.myfootballapi.model.Team
import com.aranirahan.myfootballapi.model.TeamResponse
import com.aranirahan.myfootballapi.util.TestContextProvider
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamDetailPresenterTest {
    @Mock
    private
    lateinit var view: TeamDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetail() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "1234"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(id)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamDetail(id)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamDetail(teams)
        Mockito.verify(view).hideLoading()
    }

}
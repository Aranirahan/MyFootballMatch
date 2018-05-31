package com.aranirahan.myfootballapi.view.myActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.*
import com.aranirahan.myfootballapi.view.myUtils.KEY
import com.aranirahan.myfootballapi.R.color.colorAccent
import com.aranirahan.myfootballapi.R.string.empty_data
import com.aranirahan.myfootballapi.R.string.league_id
import com.aranirahan.myfootballapi.model.ApiRepository
import com.aranirahan.myfootballapi.model.MatchEvent
import com.aranirahan.myfootballapi.presenter.MainPresenter
import com.aranirahan.myfootballapi.view.myAdapter.MainAdapter
import com.aranirahan.myfootballapi.view.myUtils.invisible
import com.aranirahan.myfootballapi.view.myInterface.MainView
import com.aranirahan.myfootballapi.view.myUtils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<MatchEvent>?) {
        swipeRefresh.isRefreshing = false
        matchEvent?.clear()
        data?.let {
            matchEvent.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast(getString(empty_data))
    }

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            textView ("FOOTBALL PAST MATCH"){
                gravity = Gravity.CENTER
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerInParent()
                    }
                }
            }
        }

        adapter = MainAdapter(matchEvent) {
            startActivity<DetailActivity>(
                    KEY.HOME_ID_KEY to it.homeId,
                    KEY.AWAY_ID_KEY to it.awayId,
                    KEY.EVENT_ID_KEY to it.idEvent)
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getMatchList(getString(league_id))

        swipeRefresh.onRefresh {
            presenter.getMatchList(getString(league_id))
        }
    }
}
package com.aranirahan.myfootballapi.view.myFragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.model.Favorite
import com.aranirahan.myfootballapi.model.database
import com.aranirahan.myfootballapi.view.myActivity.DetailActivity
import com.aranirahan.myfootballapi.view.myAdapter.FavoriteMatchAdapter
import com.aranirahan.myfootballapi.view.myUtils.MyKEY
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*


class FavoriteMatchFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var favoriteMatchEvent: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(favoriteMatchEvent) {
                        startActivity<DetailActivity>(
                    MyKEY.HOME_ID_KEY to it.idHomeTeam,
                    MyKEY.AWAY_ID_KEY to it.idAwayTeam,
                    MyKEY.EVENT_ID_KEY to it.idEvent)
        }
        listTeam.adapter = adapter
        showFavorite()

        swipeRefresh.onRefresh {
            favoriteMatchEvent.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favoriteMatchEvent.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            textView("FAVORITE MATCH") {
                gravity = Gravity.CENTER
            }.lparams {
                width = matchParent
                height = wrapContent
                marginEnd = dip(12)
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.rv_list_team_favorite
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}
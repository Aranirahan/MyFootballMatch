package com.aranirahan.myfootballapi.match.pastMatch

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*

import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.model.MatchEvent
import com.aranirahan.myfootballapi.detail.detailMatch.DetailActivity
import com.aranirahan.myfootballapi.match.MatchView
import com.aranirahan.myfootballapi.match.TeamsAdapter
import com.aranirahan.myfootballapi.util.MyKEY
import com.aranirahan.myfootballapi.util.invisible
import com.aranirahan.myfootballapi.util.visible
import com.aranirahan.myfootballapi.R.array.match_spinner
import com.aranirahan.myfootballapi.R.string.past_match
import com.aranirahan.myfootballapi.match.nextMatch.NextMatchPresenter
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PastMatchFragment : Fragment(), AnkoComponent<Context>, MatchView {

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var spinner: Spinner

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var pastMatchPresenter: PastMatchPresenter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var adapter: TeamsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TeamsAdapter(matchEvent) {
            startActivity<DetailActivity>(
                    MyKEY.HOME_ID_KEY to it.idHomeTeam,
                    MyKEY.AWAY_ID_KEY to it.idAwayTeam,
                    MyKEY.EVENT_ID_KEY to it.idEvent)
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        pastMatchPresenter = PastMatchPresenter(this, request, gson)
        nextMatchPresenter = NextMatchPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(match_spinner)
        val spinnerAdapter = ArrayAdapter(ctx, R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (spinner.selectedItem == getString(past_match)) {
                    pastMatchPresenter.getMatchList(getString(R.string.match_id))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(R.string.match_id))
                    }
                } else {
                    nextMatchPresenter.getMatchList(getString(R.string.match_id))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(R.string.match_id))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
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

            spinner = spinner()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.rv_list_team
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
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchEventList(data: List<MatchEvent>?) {
        swipeRefresh.isRefreshing = false
        matchEvent.clear()
        data?.let {
            matchEvent.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast(getString(R.string.empty_data))
    }
}


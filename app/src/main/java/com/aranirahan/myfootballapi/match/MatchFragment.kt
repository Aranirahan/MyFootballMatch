package com.aranirahan.myfootballapi.match

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.design.R.layout.support_simple_spinner_dropdown_item
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*

import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.array.league
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.model.MatchEvent
import com.aranirahan.myfootballapi.detail.detailMatch.DetailActivity
import com.aranirahan.myfootballapi.util.MyKEY
import com.aranirahan.myfootballapi.util.invisible
import com.aranirahan.myfootballapi.util.visible
import com.aranirahan.myfootballapi.R.array.match_spinner
import com.aranirahan.myfootballapi.R.id.et_search
import com.aranirahan.myfootballapi.R.string.*
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragment : Fragment(), AnkoComponent<Context>, MatchView {

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var fieldSearch: EditText
    private lateinit var searchButton: ImageButton

    private lateinit var spinnerTime: Spinner
    private lateinit var spinnerLeague: Spinner

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var pastMatchPresenter: PastMatchPresenter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var adapter: TeamsAdapter

    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fieldSearch = view!!.findViewById(et_search)

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

        val spinnerItemsTime = resources.getStringArray(match_spinner)
        val spinnerAdapterTime = ArrayAdapter(ctx, support_simple_spinner_dropdown_item, spinnerItemsTime)
        spinnerTime.adapter = spinnerAdapterTime

        val spinnerItemsLeague = resources.getStringArray(league)
        val spinnerAdapterLeague = ArrayAdapter(ctx, support_simple_spinner_dropdown_item, spinnerItemsLeague)
        spinnerLeague.adapter = spinnerAdapterLeague

        pastMatchPresenter.getMatchList(getString(match_english_pemier_league),
                getString(R.string.empty_parameter))

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            @SuppressLint("StringFormatInvalid")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (spinnerTime.selectedItem == getString(past_match) &&
                        spinnerLeague.selectedItem == getString(english_pemier_league)) {

                    pastMatchPresenter.getMatchList(getString(match_english_pemier_league),
                            getString(R.string.empty_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(match_english_pemier_league),
                                getString(R.string.empty_parameter))
                    }

                }
                if (spinnerTime.selectedItem == getString(past_match) &&
                        spinnerLeague.selectedItem == getString(english_league_championship)) {

                    pastMatchPresenter.getMatchList(getString(match_english_league_championship),
                            getString(R.string.empty_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(match_english_league_championship),
                                getString(R.string.empty_parameter))
                    }
                }
                if (spinnerTime.selectedItem == getString(past_match) &&
                        spinnerLeague.selectedItem == getString(german_bundes_liga)) {

                    pastMatchPresenter.getMatchList(getString(match_german_bundes_liga),
                            getString(R.string.empty_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(match_german_bundes_liga),
                                getString(R.string.empty_parameter))
                    }

                }
                if (spinnerTime.selectedItem == getString(past_match) &&
                        spinnerLeague.selectedItem == getString(italian_serie_A)) {

                    pastMatchPresenter.getMatchList(getString(match_italian_serie_A),
                            getString(R.string.empty_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(match_italian_serie_A),
                                getString(R.string.empty_parameter))
                    }

                }
                if (spinnerTime.selectedItem == getString(past_match) &&
                        spinnerLeague.selectedItem == getString(french_ligue_1)) {

                    pastMatchPresenter.getMatchList(getString(match_french_ligue_1),
                            getString(R.string.empty_parameter))

                    swipeRefresh.onRefresh {
                        pastMatchPresenter.getMatchList(getString(match_french_ligue_1),
                                getString(R.string.empty_parameter))
                    }

                }
                if (spinnerTime.selectedItem == getString(nextMatch) &&
                        spinnerLeague.selectedItem == getString(english_pemier_league)) {

                    nextMatchPresenter.getMatchList(getString(match_english_pemier_league))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(match_english_pemier_league))
                    }

                }
                if (spinnerTime.selectedItem == getString(nextMatch) &&
                        spinnerLeague.selectedItem == getString(english_league_championship)) {

                    nextMatchPresenter.getMatchList(getString(match_english_league_championship))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(match_english_league_championship))
                    }

                }
                if (spinnerTime.selectedItem == getString(nextMatch) &&
                        spinnerLeague.selectedItem == getString(german_bundes_liga)) {

                    nextMatchPresenter.getMatchList(getString(match_german_bundes_liga))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(match_german_bundes_liga))
                    }

                }
                if (spinnerTime.selectedItem == getString(nextMatch) &&
                        spinnerLeague.selectedItem == getString(italian_serie_A)) {

                    nextMatchPresenter.getMatchList(getString(match_italian_serie_A))

                    swipeRefresh.onRefresh {
                        nextMatchPresenter.getMatchList(getString(match_italian_serie_A))
                    }

                }
                if (spinnerTime.selectedItem == getString(nextMatch) &&
                        spinnerLeague.selectedItem == getString(french_ligue_1)) {

                    nextMatchPresenter.getMatchList(getString(match_french_ligue_1))
                }

                swipeRefresh.onRefresh {
                    nextMatchPresenter.getMatchList(getString(match_french_ligue_1))
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerTime.onItemSelectedListener = spinnerLeague.onItemSelectedListener
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

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL

                fieldSearch = editText {
                    singleLine = true
                    id = et_search
                    hint = "Search Match"
                }.lparams(width = dip(0), height = wrapContent, weight = 5f)

                searchButton = imageButton {
                    imageResource = R.drawable.ic_search_black_24dp
                    backgroundColor = 80000000
                    onClick {
                        pastMatchPresenter.getMatchList(getString(R.string.empty_parameter),
                                fieldSearch.textValue())
                    }
                }.lparams(width = dip(0), height = wrapContent, weight = 1f)

            }

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL

                spinnerTime = spinner {}
                        .lparams(width = dip(0), height = wrapContent, weight = 1f)

                spinnerLeague = spinner {}
                        .lparams(width = dip(0), height = wrapContent, weight = 1f)
            }

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

    fun EditText.textValue() = text.toString()
}


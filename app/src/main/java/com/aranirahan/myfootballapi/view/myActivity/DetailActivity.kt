package com.aranirahan.myfootballapi.view.myActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.model.MatchEvent
import com.aranirahan.myfootballapi.model.Team
import com.aranirahan.myfootballapi.model.api.ApiRepository
import com.aranirahan.myfootballapi.presenter.DetailPresenter
import com.aranirahan.myfootballapi.presenter.MatchDetailPresenter
import com.aranirahan.myfootballapi.view.myInterface.DetailView
import com.aranirahan.myfootballapi.view.myInterface.MainView
import com.aranirahan.myfootballapi.view.myUtils.MyKEY
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), DetailView, MainView {

    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var idEvent: String

    private lateinit var txtHomeName: TextView
    private lateinit var txtHomeScore: TextView
    private lateinit var txtHomeFormation: TextView
    private lateinit var txtHomeGoals: TextView
    private lateinit var txtHomeShots: TextView
    private lateinit var txtHomeGoalkeeper: TextView
    private lateinit var txtHomeDefense: TextView
    private lateinit var txtHomeForward: TextView
    private lateinit var txtHomeSubtitutes: TextView
    private lateinit var txtHomeMidfield: TextView
    private lateinit var imgHome: ImageView

    private lateinit var txtAwayName: TextView
    private lateinit var txtAwayScore: TextView
    private lateinit var txtAwayFormation: TextView
    private lateinit var txtAwayGoals: TextView
    private lateinit var txtAwayShots: TextView
    private lateinit var txtAwayGoalkeeper: TextView
    private lateinit var txtAwayDefense: TextView
    private lateinit var txtAwayForward: TextView
    private lateinit var txtAwaySubtitutes: TextView
    private lateinit var txtAwayMidfield: TextView
    private lateinit var imgAway: ImageView

    private lateinit var txtMatchDate: TextView

    private lateinit var progressBar: ProgressBar

    private lateinit var detailPresenter: DetailPresenter
    private lateinit var detailMatchPresenter: MatchDetailPresenter

    private lateinit var team: Team
    private lateinit var team2: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        txtHomeName = findViewById(R.id.home_name)
        txtHomeScore = findViewById(R.id.home_score_match)
        txtHomeGoals = findViewById(R.id.home_goals)
        txtHomeShots = findViewById(R.id.home_shots)
        txtHomeGoalkeeper = findViewById(R.id.home_goalkeeper)
        txtHomeDefense = findViewById(R.id.home_defense)
        txtHomeForward = findViewById(R.id.away_forward)
        txtHomeSubtitutes = findViewById(R.id.home_substitutes)
        txtHomeMidfield = findViewById(R.id.home_midfield)
        imgHome = findViewById(R.id.img_home)

        txtAwayName = findViewById(R.id.away_name)
        txtAwayScore = findViewById(R.id.away_score_match)
        txtAwayGoals = findViewById(R.id.away_goals)
        txtAwayShots = findViewById(R.id.home_shots)
        txtAwayGoalkeeper = findViewById(R.id.away_goalkeeper)
        txtAwaySubtitutes = findViewById(R.id.away_substitutes)
        txtAwayDefense = findViewById(R.id.away_defense)
        txtAwayMidfield = findViewById(R.id.away_midfield)
        txtAwayForward = findViewById(R.id.away_forward)
        imgAway = findViewById(R.id.img_away)

        txtMatchDate = findViewById(R.id.match_date)
        progressBar = findViewById(R.id.progress_bar_detail)

        val i = intent

        idHomeTeam = i.getStringExtra(MyKEY.HOME_ID_KEY)
        idAwayTeam = i.getStringExtra(MyKEY.AWAY_ID_KEY)
        idEvent = i.getStringExtra(MyKEY.EVENT_ID_KEY)

        val request = ApiRepository()
        val gson = Gson()

        detailPresenter = DetailPresenter(this, request, gson)
        detailMatchPresenter = MatchDetailPresenter(this, request, gson)

        detailPresenter.geDetailTeamList(idHomeTeam, idAwayTeam)
        detailMatchPresenter.getDetailMatch(idEvent)
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.invisible() {
        visibility = View.GONE
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamsList(dataA: List<Team>?, dataB: List<Team>?) {
        team = Team(dataA?.get(0)?.strTeamBadge)
        team2 = Team(dataB?.get(0)?.strTeamBadge)
        Picasso.get().load(dataA?.get(0)?.strTeamBadge).into(imgHome)
        Picasso.get().load(dataB?.get(0)?.strTeamBadge).into(imgAway)
    }

    override fun showMatchEventList(data: List<MatchEvent>?) {
        txtHomeName.text = data?.get(0)?.strHomeTeam ?: "-"
        txtHomeScore.text = data?.get(0)?.intHomeScore ?: "-"
//        txtHomeFormation.text = data?.get(0)?.strHomeFormation ?: "-"
        txtHomeGoals.text = data?.get(0)?.strHomeGoalDetails ?: "-"
        txtHomeGoalkeeper.text = data?.get(0)?.strHomeLineupGoalkeeper ?: "-"
        txtHomeShots.text = data?.get(0)?.intHomeShots ?: "-"
        txtHomeDefense.text = data?.get(0)?.strHomeLineupDefense ?: "-"
        txtHomeForward.text = data?.get(0)?.strHomeLineupForward ?: "-"
        txtHomeSubtitutes.text = data?.get(0)?.strHomeLineupSubstitutes ?: "-"
        txtHomeMidfield.text = data?.get(0)?.strAwayLineupDefense ?: "-"

        txtAwayName.text = data?.get(0)?.strAwayTeam ?: "-"
        txtAwayScore.text = data?.get(0)?.intAwayScore ?: "-"
//        txtAwayFormation.text = data?.get(0)?.strAwayFormation ?: "-"
        txtAwayGoals.text = data?.get(0)?.strAwayGoalDetails ?: "-"
        txtAwayGoalkeeper.text = data?.get(0)?.strAwayLineupGoalkeeper ?: "-"
        txtAwayShots.text = data?.get(0)?.intAwayShots ?: "-"
        txtAwayDefense.text = data?.get(0)?.awayDefense ?: "-"
        txtAwayForward.text = data?.get(0)?.strAwayLineupForward ?: "-"
        txtAwaySubtitutes.text = data?.get(0)?.strAwayLineupSubstitutes ?: "-"
        txtAwayMidfield.text = data?.get(0)?.strAwayLineupMidfield ?: "-"

        txtMatchDate.text = data?.get(0)?.dateEvent ?: "-"
    }
}

package com.aranirahan.myfootballapi.view.myActivity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.drawable.ic_add_to_favorites
import com.aranirahan.myfootballapi.R.drawable.ic_added_to_favorites
import com.aranirahan.myfootballapi.R.id.*
import com.aranirahan.myfootballapi.R.menu.detail_menu
import com.aranirahan.myfootballapi.model.Favorite
import com.aranirahan.myfootballapi.model.item.MatchEvent
import com.aranirahan.myfootballapi.model.item.Team
import com.aranirahan.myfootballapi.model.api.ApiRepository
import com.aranirahan.myfootballapi.model.database
import com.aranirahan.myfootballapi.presenter.DetailPresenter
import com.aranirahan.myfootballapi.presenter.MatchDetailPresenter
import com.aranirahan.myfootballapi.view.myInterface.DetailView
import com.aranirahan.myfootballapi.view.myInterface.TeamsView
import com.aranirahan.myfootballapi.view.myUtils.MyKEY
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

import kotlinx.android.synthetic.main.activity_detail.view.*


class DetailActivity : AppCompatActivity(), DetailView, TeamsView {

    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var idEvent: String

    private lateinit var txtHomeName: TextView
    private lateinit var txtHomeScore: TextView
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

    private lateinit var teamA: Team
    private lateinit var teamB: Team
    private lateinit var matchEvent: MatchEvent

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

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

        val myIntent = intent

        idHomeTeam = myIntent.getStringExtra(MyKEY.HOME_ID_KEY)
        idAwayTeam = myIntent.getStringExtra(MyKEY.AWAY_ID_KEY)
        idEvent = myIntent.getStringExtra(MyKEY.EVENT_ID_KEY)

        favoriteState()
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
        teamA = Team(dataA?.get(0)?.strTeamBadge)
        teamB = Team(dataB?.get(0)?.strTeamBadge)
        Picasso.get().load(dataA?.get(0)?.strTeamBadge).into(imgHome)
        Picasso.get().load(dataB?.get(0)?.strTeamBadge).into(imgAway)
    }

    override fun showMatchEventList(data: List<MatchEvent>?) {
        matchEvent = MatchEvent(data?.get(0)?.idEvent,
                data?.get(0)?.strHomeTeam,
                data?.get(0)?.strAwayTeam,
                data?.get(0)?.intHomeScore,
                data?.get(0)?.intAwayScore,
                data?.get(0)?.dateEvent,
                data?.get(0)?.strHomeLineupGoalkeeper,
                data?.get(0)?.strAwayLineupGoalkeeper,
                data?.get(0)?.strHomeGoalDetails,
                data?.get(0)?.strAwayGoalDetails,
                data?.get(0)?.intHomeShots,
                data?.get(0)?.intAwayShots,
                data?.get(0)?.strHomeLineupDefense,
                data?.get(0)?.awayDefense,
                data?.get(0)?.strAwayLineupDefense,
                data?.get(0)?.strAwayLineupMidfield,
                data?.get(0)?.strHomeLineupForward,
                data?.get(0)?.strAwayLineupForward,
                data?.get(0)?.strHomeLineupSubstitutes,
                data?.get(0)?.strAwayLineupSubstitutes,
                data?.get(0)?.strHomeFormation,
                data?.get(0)?.strAwayFormation,
                data?.get(0)?.strTeamBadge,
                data?.get(0)?.idHomeTeam,
                data?.get(0)?.idAwayTeam)

        txtHomeName.text = data?.get(0)?.strHomeTeam
        txtHomeScore.text = data?.get(0)?.intHomeScore
        txtHomeGoals.text = data?.get(0)?.strHomeGoalDetails
        txtHomeGoalkeeper.text = data?.get(0)?.strHomeLineupGoalkeeper
        txtHomeShots.text = data?.get(0)?.intHomeShots
        txtHomeDefense.text = data?.get(0)?.strHomeLineupDefense
        txtHomeForward.text = data?.get(0)?.strHomeLineupForward
        txtHomeSubtitutes.text = data?.get(0)?.strHomeLineupSubstitutes
        txtHomeMidfield.text = data?.get(0)?.strAwayLineupDefense

        txtAwayName.text = data?.get(0)?.strAwayTeam
        txtAwayScore.text = data?.get(0)?.intAwayScore
        txtAwayGoals.text = data?.get(0)?.strAwayGoalDetails
        txtAwayGoalkeeper.text = data?.get(0)?.strAwayLineupGoalkeeper
        txtAwayShots.text = data?.get(0)?.intAwayShots
        txtAwayDefense.text = data?.get(0)?.awayDefense
        txtAwayForward.text = data?.get(0)?.strAwayLineupForward
        txtAwaySubtitutes.text = data?.get(0)?.strAwayLineupSubstitutes
        txtAwayMidfield.text = data?.get(0)?.strAwayLineupMidfield

        txtMatchDate.text = data?.get(0)?.dateEvent
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to matchEvent.idEvent,
                        Favorite.STR_HOME_TEAM to matchEvent.strHomeTeam,
                        Favorite.STR_AWAY_TEAM to matchEvent.strAwayTeam,
                        Favorite.INT_HOME_SCORE to matchEvent.intHomeScore,
                        Favorite.INT_AWAY_SCORE to matchEvent.intAwayScore,
                        Favorite.DATE_EVENT to matchEvent.dateEvent,
                        Favorite.STR_HOME_LINEUP_GOALKEEPER to matchEvent.strHomeLineupGoalkeeper,
                        Favorite.STR_AWAY_LINEUP_GOALKEEPER to matchEvent.strAwayLineupGoalkeeper,
                        Favorite.STR_HOME_GOAL_DETAILS to matchEvent.strHomeGoalDetails,
                        Favorite.STR_AWAY_GOAL_DETAILS to matchEvent.strAwayGoalDetails,
                        Favorite.INT_HOME_SHOTS to matchEvent.intHomeShots,
                        Favorite.INT_AWAY_SHOTS to matchEvent.intAwayShots,
                        Favorite.STR_HOME_LINEUP_DEFENSE to matchEvent.strHomeLineupDefense,
                        Favorite.AWAY_DEFENSE to matchEvent.awayDefense,
                        Favorite.STR_AWAY_LINEUP_DEFENSE to matchEvent.strAwayLineupDefense,
                        Favorite.STR_AWAY_LINEUP_MIDFIELD to matchEvent.strAwayLineupMidfield,
                        Favorite.STR_HOME_LINEUP_FORWARD to matchEvent.strHomeLineupForward,
                        Favorite.STR_AWAY_LINEUP_FORWARD to matchEvent.strAwayLineupForward,
                        Favorite.STR_HOME_LINEUP_SUBSTITUTES to matchEvent.strHomeLineupSubstitutes,
                        Favorite.STR_AWAY_LINEUP_SUBSTITUTES to matchEvent.strAwayLineupSubstitutes,
                        Favorite.STR_HOME_FORMATION to matchEvent.strHomeFormation,
                        Favorite.STR_AWAY_FORMATION to matchEvent.strAwayFormation,
                        Favorite.STR_TEAM_BADGE to matchEvent.strTeamBadge,
                        Favorite.ID_HOME_TEAM to matchEvent.idHomeTeam,
                        Favorite.ID_AWAY_TEAM to matchEvent.idAwayTeam)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast("Can't add to favorite")
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,
                        "(ID_EVENT = {idEvent})",
                        "idEvent" to idEvent)
            }
            toast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            toast("Can't remove from favorite")
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                    ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                    ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {idEvent})", "idEvent" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}

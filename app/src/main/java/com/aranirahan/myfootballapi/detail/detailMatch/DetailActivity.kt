package com.aranirahan.myfootballapi.detail.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import com.aranirahan.myfootballapi.db.Favorite
import com.aranirahan.myfootballapi.model.MatchEvent
import com.aranirahan.myfootballapi.model.Team
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.db.database
import com.aranirahan.myfootballapi.match.MatchView
import com.aranirahan.myfootballapi.util.MyKEY
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DetailActivity : AppCompatActivity(), DetailView, MatchView {

    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var idEvent: String

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

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun showTeamsList(data: List<Team>?, data2: List<Team>?) {
        teamA = Team(data?.get(0)?.strTeamBadge)
        teamB = Team(data2?.get(0)?.strTeamBadge)
        Picasso.get().load(data?.get(0)?.strTeamBadge).into(img_home)
        Picasso.get().load(data2?.get(0)?.strTeamBadge).into(img_away)
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

        home_name.text = data?.get(0)?.strHomeTeam
        home_score_match.text = data?.get(0)?.intHomeScore
        home_goals.text = data?.get(0)?.strHomeGoalDetails
        home_goalkeeper.text = data?.get(0)?.strHomeLineupGoalkeeper
        home_shots.text = data?.get(0)?.intHomeShots
        home_defense.text = data?.get(0)?.strHomeLineupDefense
        home_forward.text = data?.get(0)?.strHomeLineupForward
        home_substitutes.text = data?.get(0)?.strHomeLineupSubstitutes
        home_midfield.text = data?.get(0)?.strAwayLineupDefense

        away_name.text = data?.get(0)?.strAwayTeam
        away_score_match.text = data?.get(0)?.intAwayScore
        away_goals.text = data?.get(0)?.strAwayGoalDetails
        away_goalkeeper.text = data?.get(0)?.strAwayLineupGoalkeeper
        away_shot.text = data?.get(0)?.intAwayShots
        away_defense.text = data?.get(0)?.awayDefense
        away_forward.text = data?.get(0)?.strAwayLineupForward
        away_substitutes.text = data?.get(0)?.strAwayLineupSubstitutes
        away_midfield.text = data?.get(0)?.strAwayLineupMidfield

        match_date.text = data?.get(0)?.dateEvent
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
            Snackbar.make(findViewById(R.id.ll_detail),
                    "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(findViewById(R.id.ll_detail),
                    "Can't add to favorite", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,
                        "(ID_EVENT = {idEvent})",
                        "idEvent" to idEvent)
            }
            Snackbar.make(findViewById(R.id.ll_detail),
                    "Removed from favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(findViewById(R.id.ll_detail),
                    "Can't remove from favorite", Snackbar.LENGTH_SHORT).show()
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

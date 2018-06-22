package com.aranirahan.myfootballapi.detail.detailTeam.detailActivity

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.drawable.ic_add_to_favorites
import com.aranirahan.myfootballapi.R.drawable.ic_added_to_favorites
import com.aranirahan.myfootballapi.R.id.add_to_favorite
import com.aranirahan.myfootballapi.R.menu.detail_menu
import com.aranirahan.myfootballapi.api.ApiRepository
import com.aranirahan.myfootballapi.db.FavoriteTeam
import com.aranirahan.myfootballapi.db.database
import com.aranirahan.myfootballapi.detail.detailTeam.description.TeamDetailPresenter
import com.aranirahan.myfootballapi.detail.detailTeam.description.TeamDetailView
import com.aranirahan.myfootballapi.model.Team
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


@SuppressLint("Registered")
class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String
    private lateinit var checkNull: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentAdapter = TeamPagerAdapter(id, supportFragmentManager, 2)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].strTeamBadge
        )

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
                try {
                    if (isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                } catch (e: Exception) {
                    toast("Still loading, try again")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to teams.strTeamBadge,
                        FavoriteTeam.TEAM_NAME to teams.teamId,
                        FavoriteTeam.TEAM_BADGE to teams.teamName)
            }
            Snackbar.make(findViewById(R.id.ll_team),
                    "Added to favorite",
                    Snackbar.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException) {
            Snackbar.make(findViewById(R.id.ll_team),
                    "Can't add to favorite, coz" + e.localizedMessage,
                    Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to id)
            }
            Snackbar.make(findViewById(R.id.ll_team),
                    "Removed from favorite", Snackbar.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException) {
            Snackbar.make(findViewById(R.id.ll_team),
                    "Can't remove from favorite, coz" + e.localizedMessage,
                    Snackbar.LENGTH_SHORT).show()
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
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}

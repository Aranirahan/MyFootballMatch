package com.aranirahan.myfootballapi.home

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.id.*
import com.aranirahan.myfootballapi.favorite.favoriteMatch.FavoriteMatchFragment
import com.aranirahan.myfootballapi.favorite.favoriteTeam.FavoriteTeamsFragment
import com.aranirahan.myfootballapi.match.pastMatch.PastMatchFragment
import com.aranirahan.myfootballapi.team.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                pastMatch -> {
                    loadPastMatchFragment(savedInstanceState)
                }
                nextMatch -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
                favorites_team -> {
                    loadFavoritesTeamFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = pastMatch
    }

    private fun loadPastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PastMatchFragment()
                            ,PastMatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment()
                            , TeamsFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment()
                            ,FavoriteMatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment()
                            ,FavoriteTeamsFragment::class.simpleName)
                    .commit()
        }
    }
}

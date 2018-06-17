package com.aranirahan.myfootballapi.view.myActivity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.id.*
import com.aranirahan.myfootballapi.view.myFragment.FavoriteMatchFragment
import com.aranirahan.myfootballapi.view.myFragment.NextMatchFragment
import com.aranirahan.myfootballapi.view.myFragment.PastMatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnNavigationItemSelectedListener({ item ->
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
            }
            true
        })
        bottom_navigation.selectedItemId = pastMatch
    }

    private fun loadPastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PastMatchFragment()
                            , PastMatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchFragment()
                            , NextMatchFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment()
                            , FavoriteMatchFragment::class.simpleName)
                    .commit()
        }
    }
}

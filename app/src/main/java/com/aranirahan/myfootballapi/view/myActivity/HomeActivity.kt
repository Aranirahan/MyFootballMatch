package com.aranirahan.myfootballapi.view.myActivity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aranirahan.myfootballapi.R
import com.aranirahan.myfootballapi.R.id.favorites
import com.aranirahan.myfootballapi.R.id.teams
import com.aranirahan.myfootballapi.view.myFragment.FavoriteTeamsFragment
import com.aranirahan.myfootballapi.view.myFragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                    Log.d("Teamku :" ,"true")
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                    Log.d("myFavorite :" ,"true")
                }
            }
            true
        })
        bottom_navigation.selectedItemId = teams
    }

    private fun loadTeamsFragment (savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment()
                            ,TeamsFragment::class.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment (savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment()
                            ,FavoriteTeamsFragment::class.simpleName)
                    .commit()
        }
    }
}

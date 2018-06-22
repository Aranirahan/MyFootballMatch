package com.aranirahan.myfootballapi.detail.detailTeam.detailActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aranirahan.myfootballapi.detail.detailTeam.description.DescriptionTeamFragment
import com.aranirahan.myfootballapi.detail.detailTeam.player.PlayerTeamFragment

class TeamPagerAdapter(private val idTeam: String, fm: FragmentManager, private var tabCount: Int)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                newInstance(idTeam)
            }
            1 -> {
                newInstancePlayer(idTeam)
            }
            else -> {
                return PlayerTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Description"
            else -> {
                return "Player"
            }
        }
    }

    companion object {
        const val KEY_TEAM = "KEY_TEAM"
        const val KEY_TEAM_2 = "KEY_TEAM_2"
        fun newInstance(id: String): DescriptionTeamFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM, id)

            val desciptionTeamFragment = DescriptionTeamFragment()
            desciptionTeamFragment.arguments = bindData
            return desciptionTeamFragment
        }

        fun newInstancePlayer(id: String): PlayerTeamFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM_2, id)

            val playerTeamFragment = PlayerTeamFragment()
            playerTeamFragment.arguments = bindData
            return playerTeamFragment
        }
    }
}


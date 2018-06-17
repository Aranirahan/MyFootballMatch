package com.aranirahan.myfootballapi.model

data class Favorite(
        val id: Long?,
        val idEvent: String?,
        val strHomeTeam: String? = null,
        val strAwayTeam: String? = null,
        val intHomeScore: String? = null,
        val intAwayScore: String? = null,
        val dateEvent: String? = null,
        val strHomeLineupGoalkeeper: String? = null,
        val strAwayLineupGoalkeeper: String? = null,
        val strHomeGoalDetails: String? = null,
        val strAwayGoalDetails: String? = null,
        val intHomeShots: String? = null,
        val intAwayShots: String? = null,
        val strHomeLineupDefense: String? = null,
        val awayDefense: String? = null,
        val strAwayLineupDefense: String? = null,
        val strAwayLineupMidfield: String? = null,
        val strHomeLineupForward: String? = null,
        val strAwayLineupForward: String? = null,
        val strHomeLineupSubstitutes: String? = null,
        val strAwayLineupSubstitutes: String? = null,
        val strHomeFormation: String? = null,
        val strAwayFormation: String? = null,
        val strTeamBadge: String? = null,
        val idHomeTeam: String? = null,
        val idAwayTeam: String? = null) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        val ID: String = "ID_"
        val ID_EVENT: String = "ID_EVENT"
        val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        val DATE_EVENT: String = "DATE_EVENT"
        val STR_HOME_LINEUP_GOALKEEPER: String = "STR_HOME_LINEUP_GOALKEEPER"
        val STR_AWAY_LINEUP_GOALKEEPER: String = "STR_AWAY_LINEUP_GOALKEEPER"
        val STR_HOME_GOAL_DETAILS: String = "STR_HOME_GOAL_DETAILS"
        val STR_AWAY_GOAL_DETAILS: String = "STR_AWAY_GOAL_DETAILS"
        val INT_HOME_SHOTS: String = "INT_HOME_SHOTS"
        val INT_AWAY_SHOTS: String = "INT_AWAY_SHOTS"
        val STR_HOME_LINEUP_DEFENSE: String = "STR_HOME_LINEUP_DEFENSE"
        val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        val STR_AWAY_LINEUP_DEFENSE: String = "STR_AWAY_LINEUP_DEFENSE"
        val STR_AWAY_LINEUP_MIDFIELD: String = "STR_AWAY_LINEUP_MIDFIELD"
        val STR_HOME_LINEUP_FORWARD: String = "STR_HOME_LINEUP_FORWARD"
        val STR_AWAY_LINEUP_FORWARD: String = "STR_AWAY_LINEUP_FORWARD"
        val STR_HOME_LINEUP_SUBSTITUTES: String = "STR_HOME_LINEUP_SUBSTITUTES"
        val STR_AWAY_LINEUP_SUBSTITUTES: String = "STR_AWAY_LINEUP_SUBSTITUTES"
        val STR_HOME_FORMATION: String = "STR_HOME_FORMATION"
        val STR_AWAY_FORMATION: String = "STR_AWAY_FORMATION"
        val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
    }
}

package com.aranirahan.myfootballapi.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
        "myFovorite.db",
        null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT + UNIQUE,
                Favorite.STR_HOME_TEAM to TEXT,
                Favorite.STR_AWAY_TEAM to TEXT,
                Favorite.INT_HOME_SCORE to TEXT,
                Favorite.INT_AWAY_SCORE to TEXT,
                Favorite.DATE_EVENT to TEXT,
                Favorite.STR_HOME_LINEUP_GOALKEEPER to TEXT,
                Favorite.STR_AWAY_LINEUP_GOALKEEPER to TEXT,
                Favorite.STR_HOME_GOAL_DETAILS to TEXT,
                Favorite.STR_AWAY_GOAL_DETAILS to TEXT,
                Favorite.INT_HOME_SHOTS to TEXT,
                Favorite.INT_AWAY_SHOTS to TEXT,
                Favorite.STR_HOME_LINEUP_DEFENSE to TEXT,
                Favorite.AWAY_DEFENSE to TEXT,
                Favorite.STR_AWAY_LINEUP_DEFENSE to TEXT,
                Favorite.STR_AWAY_LINEUP_MIDFIELD to TEXT,
                Favorite.STR_HOME_LINEUP_FORWARD to TEXT,
                Favorite.STR_AWAY_LINEUP_FORWARD to TEXT,
                Favorite.STR_HOME_LINEUP_SUBSTITUTES to TEXT,
                Favorite.STR_AWAY_LINEUP_SUBSTITUTES to TEXT,
                Favorite.STR_HOME_FORMATION to TEXT,
                Favorite.STR_AWAY_FORMATION to TEXT,
                Favorite.STR_TEAM_BADGE to TEXT,
                Favorite.ID_HOME_TEAM to TEXT,
                Favorite.ID_AWAY_TEAM to TEXT)

        db?.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID_FAVORITE_TEAM to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)

    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
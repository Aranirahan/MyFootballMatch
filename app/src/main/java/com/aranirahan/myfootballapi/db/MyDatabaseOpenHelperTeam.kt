package com.aranirahan.myfootballapi.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelperTeam(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
        "FovoriteTeam.db",
        null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelperTeam? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelperTeam {
            if (instance == null) {
                instance = MyDatabaseOpenHelperTeam(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelperTeam
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID_FAVORITE_TEAM to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.databaseTeam: MyDatabaseOpenHelperTeam
    get() = MyDatabaseOpenHelperTeam.getInstance(applicationContext)
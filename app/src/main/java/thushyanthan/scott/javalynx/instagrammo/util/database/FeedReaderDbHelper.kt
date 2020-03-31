package thushyanthan.scott.javalynx.instagrammo.util.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION ) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Queries.SQL_CREATE_ENTRIES_POSTS)
        db?.execSQL(Queries.SQL_CREATE_ENTRIES_FOLLOWERS)
        db?.execSQL(Queries.SQL_CREATE_ENTRIES_PROFILE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Queries.SQL_DELETE_ENTRIES_POSTS)
        db?.execSQL(Queries.SQL_DELETE_ENTRIES_FOLLOWERS)
        db?.execSQL(Queries.SQL_DELETE_ENTRIES_PROFILE)
        onCreate(db)
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "InstagrammoLallot.db"
    }
}
package com.mst.instagrammo.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_POSTENTRY)
        db?.execSQL(SQL_CREATE_FOLLOWERENTRY)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_NAME = "MyDatabase.db"
        const val DATABASE_VERSION = 1

        const val SQL_CREATE_POSTENTRY =
            "CREATE TABLE ${DBContract.PostEntry.TABLE_NAME} (" +
                "${DBContract.PostEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                "${DBContract.PostEntry.COLUMN_NAME_TITLE} TEXT," +
                "${DBContract.PostEntry.COLUMN_NAME_UPLOADTIME} TEXT," +
                "${DBContract.PostEntry.COLUMN_NAME_PROFILEID} TEXT," +
                "CONSTRAINT fk_profile" +
                "FOREIGN KEY (${DBContract.PostEntry.COLUMN_NAME_PROFILEID}) " +
                    "REFERENCES (${DBContract.FollowerEntry.TABLE_NAME} (${DBContract.FollowerEntry.COLUMN_NAME_ID}))" +
            ")"

        const val SQL_CREATE_FOLLOWERENTRY =
            "CREATE TABLE ${DBContract.FollowerEntry.TABLE_NAME} (" +
                    "${DBContract.FollowerEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                    "${DBContract.FollowerEntry.COLUMN_NAME_NAME} TEXT," +
                    "${DBContract.FollowerEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                    "${DBContract.FollowerEntry.COLUMN_NAME_PICTURE} TEXT," +
            ")"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DBContract.PostEntry.TABLE_NAME}, ${DBContract.FollowerEntry.TABLE_NAME}"

    }
}
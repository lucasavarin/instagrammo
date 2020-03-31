package com.example.instagrammo.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_FOLLOWERS_ENTRIES =
        """CREATE TABLE ${DatabaseContract.FollowersEntry.TABLE_NAME} (
                ${DatabaseContract.FollowersEntry.ID}               INTEGER PRIMARY KEY,
                ${DatabaseContract.FollowersEntry.DESCRIPTION}      TEXT,
                ${DatabaseContract.FollowersEntry.NAME}             TEXT,
                ${DatabaseContract.FollowersEntry.PICTURE}          TEXT
                )"""

    private val SQL_CREATE_POSTS_ENTRIES =
        """CREATE TABLE ${DatabaseContract.PostEntry.TABLE_NAME} (
                ${DatabaseContract.PostEntry.POST_ID}               INTEGER PRIMARY KEY,
                ${DatabaseContract.PostEntry.PROFILE_ID}            INTEGER,
                ${DatabaseContract.PostEntry.TITLE}                 TEXT,
                ${DatabaseContract.PostEntry.UPLOAD_TIME}           TEXT,
                ${DatabaseContract.PostEntry.PICTURE}               TEXT,
                
                CONSTRAINT fk_profile
                    FOREIGN KEY (${DatabaseContract.PostEntry.PROFILE_ID})
                    REFERENCES ${DatabaseContract.FollowersEntry.TABLE_NAME} (${DatabaseContract.FollowersEntry.ID})
                )"""

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Instagrammo.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_FOLLOWERS_ENTRIES)
        db.execSQL(SQL_CREATE_POSTS_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //stub
    }
}
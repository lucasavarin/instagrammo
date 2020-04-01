package com.example.instagrammo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.instagrammo.beans.response.HomePayloadPostBean
import com.example.instagrammo.beans.response.HomeProfilePostBean
import com.example.instagrammo.data_class.DbPost
import com.example.instagrammo.data_class.ProfiloStories

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_POST_TABLE =
        """CREATE TABLE 
             ${ContractPost.PostEntry.TABLE_NAME}(
             ${ContractPost.PostEntry.COLUMN_ID} INTEGER PRIMARY KEY, 
             ${ContractPost.PostEntry.COLUMN_TITLE} TEXT,
             ${ContractPost.PostEntry.COLUMN_PROFILE_ID} INTEGER,
             ${ContractPost.PostEntry.COLUMN_UPLOAD} TEXT,
             CONSTRAINT fk_profile
             FOREIGN KEY (${ContractPost.PostEntry.COLUMN_PROFILE_ID}) REFERENCES ${ContractFollower.FollowerEntry.TABLE_NAME}(${ContractFollower.FollowerEntry.COLUMN_ID}))
        """.trimIndent()

    private val CREATE_FOLLOWERS_TABLE =
        """CREATE TABLE
            ${ContractFollower.FollowerEntry.TABLE_NAME} (
            ${ContractFollower.FollowerEntry.COLUMN_ID} INTEGER PRIMARY KEY,
            ${ContractFollower.FollowerEntry.COLUMN_NAME} TEXT,
            ${ContractFollower.FollowerEntry.COLUMN_DESCRIPTION} TEXT,
            ${ContractFollower.FollowerEntry.COLUMN_PICTURE} TEXT)
        """.trimIndent()

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_POST_TABLE)
        db?.execSQL(CREATE_FOLLOWERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getPosts() : List<HomePayloadPostBean>{
        val db = this.readableDatabase
        val list = arrayListOf<HomePayloadPostBean>()
        val cursor = db?.query(ContractPost.PostEntry.TABLE_NAME, null, null, null, null,null, null)
        while (cursor!!.moveToNext()){
            list.add(HomePayloadPostBean(
                cursor.getString(cursor.getColumnIndexOrThrow(ContractPost.PostEntry.COLUMN_PROFILE_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContractPost.PostEntry.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContractPost.PostEntry.COLUMN_TITLE)),
                "",
                "",
                HomeProfilePostBean(cursor.getString(cursor.getColumnIndexOrThrow(ContractPost.PostEntry.COLUMN_PROFILE_ID)), "", "", "", "", "")
            ))
        }
        return list
    }

    fun savePost(post : List<DbPost>){
        val db = this.writableDatabase
        post.forEach {
            val values = ContentValues().apply {
                put(ContractPost.PostEntry.COLUMN_TITLE, it.title)
                put(ContractPost.PostEntry.COLUMN_PROFILE_ID, it.profileId)
                put(ContractPost.PostEntry.COLUMN_ID, it.postId)
                put(ContractPost.PostEntry.COLUMN_UPLOAD, it.uploadTime)
            }
            val row = db?.insert(ContractPost.PostEntry.TABLE_NAME, null, values)
        }
    }

    fun saveFollowers(follower : Array<ProfiloStories>){
        val db = this.writableDatabase
        follower.forEach {
            val values = ContentValues().apply {
                put(ContractFollower.FollowerEntry.COLUMN_ID, it.id)
                put(ContractFollower.FollowerEntry.COLUMN_DESCRIPTION, it.description)
                put(ContractFollower.FollowerEntry.COLUMN_NAME, it.name)
                put(ContractFollower.FollowerEntry.COLUMN_PICTURE, it.picture)
            }
            val row = db?.insert(ContractFollower.FollowerEntry.TABLE_NAME, null, values)
        }
    }

    fun getFollowers() : List<ProfiloStories>{
        val db = this.readableDatabase
        val array = arrayListOf<ProfiloStories>()
        val cursor = db?.query(ContractFollower.FollowerEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor!!.moveToNext()){
            array.add(ProfiloStories(
                cursor.getString(cursor.getColumnIndexOrThrow(ContractFollower.FollowerEntry.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(ContractFollower.FollowerEntry.COLUMN_NAME)),
                "",
                cursor.getString(cursor.getColumnIndexOrThrow(ContractFollower.FollowerEntry.COLUMN_PICTURE))
            ))
        }
        return array
    }

    companion object{
        const val DATABASE_NAME = "SplashDb"
        const val DATABASE_VERSION = 1
    }
}
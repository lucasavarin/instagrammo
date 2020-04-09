package com.example.instagrammo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.instagrammo.beans.business.Follower
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.Profile
import java.lang.Exception

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    private val SQL_CREATE_FOLLOWERS_ENTRIES =
        """CREATE TABLE ${DatabaseContract.FollowersProfileEntry.TABLE_NAME} (
                ${DatabaseContract.FollowersProfileEntry.ID}               INTEGER PRIMARY KEY,
                ${DatabaseContract.FollowersProfileEntry.DESCRIPTION}      TEXT,
                ${DatabaseContract.FollowersProfileEntry.NAME}             TEXT,
                ${DatabaseContract.FollowersProfileEntry.PICTURE}          TEXT,
                ${DatabaseContract.FollowersProfileEntry.POST_NUMBER}      TEXT,
                ${DatabaseContract.FollowersProfileEntry.FOLLOWERS_NUMBER} TEXT
                )"""

    private val SQL_CREATE_POSTS_ENTRIES =
        """CREATE TABLE ${DatabaseContract.PostEntry.TABLE_NAME} (
                ${DatabaseContract.PostEntry.POST_ID}               INTEGER PRIMARY KEY,
                ${DatabaseContract.PostEntry.PROFILE_ID}            INTEGER,
                ${DatabaseContract.PostEntry.TITLE}                 TEXT,
                ${DatabaseContract.PostEntry.UPLOAD_TIME}           DATE,
                ${DatabaseContract.PostEntry.PICTURE}               TEXT,
                ${DatabaseContract.PostEntry.IS_LIKED}               INTEGER default 0,
                
                CONSTRAINT fk_profile
                    FOREIGN KEY (${DatabaseContract.PostEntry.PROFILE_ID})
                    REFERENCES ${DatabaseContract.FollowersProfileEntry.TABLE_NAME} (${DatabaseContract.FollowersProfileEntry.ID})
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

    //insert
    fun insertPost(post: Post):Boolean{
        try {
            val db = this.writableDatabase

            val values = ContentValues().apply {
                put(DatabaseContract.PostEntry.PICTURE, post.picture)
                put(DatabaseContract.PostEntry.POST_ID, post.postId)
                put(DatabaseContract.PostEntry.PROFILE_ID, post.profileId)
                put(DatabaseContract.PostEntry.TITLE, post.title)
                put(DatabaseContract.PostEntry.UPLOAD_TIME, post.uploadTime)
            }

            val newRowId = db.insert(DatabaseContract.PostEntry.TABLE_NAME, null, values)
            return (newRowId == post.postId.toLong())
        } catch (e: Exception){
            return false
        }
    }

    fun insertProfile(profile: Profile):Boolean{
        try {
            val db = this.writableDatabase

            val values = ContentValues().apply {
                put(DatabaseContract.FollowersProfileEntry.DESCRIPTION, profile.description)
                put(DatabaseContract.FollowersProfileEntry.NAME, profile.name)
                put(DatabaseContract.FollowersProfileEntry.PICTURE, profile.picture)
                put(DatabaseContract.FollowersProfileEntry.ID, profile.profileId.toInt())
                put(DatabaseContract.FollowersProfileEntry.POST_NUMBER, profile.postsNumber)
                put(DatabaseContract.FollowersProfileEntry.FOLLOWERS_NUMBER, profile.followersNumber)
            }

            val newRowId = db.insert(DatabaseContract.FollowersProfileEntry.TABLE_NAME, null, values)
            return (newRowId == profile.profileId.toLong())
        } catch (e: Exception){
            return false
        }
    }

    //read
    fun getPostList(profileId:Int?):List<Post>{

        val db = this.writableDatabase

        var query = """SELECT * 
            FROM ${DatabaseContract.PostEntry.TABLE_NAME} p 
            LEFT JOIN ${DatabaseContract.FollowersProfileEntry.TABLE_NAME} f 
            ON p.${DatabaseContract.PostEntry.PROFILE_ID} = f.${DatabaseContract.FollowersProfileEntry.ID}
            """


        var selectionArgs:Array<String>? = null
        if(profileId != null){
            query += "WHERE f.${DatabaseContract.FollowersProfileEntry.ID} = ?"
            selectionArgs = arrayOf(profileId.toString(), profileId.toString())
        }

        query += "ORDER BY ${DatabaseContract.PostEntry.UPLOAD_TIME} DESC"

        val cursor = db.rawQuery(query, selectionArgs)

        val posts = mutableListOf<Post>()

        with(cursor){
            while (moveToNext()){
                posts.add(Post(
                    getLong(getColumnIndexOrThrow(DatabaseContract.PostEntry.PROFILE_ID)).toString()?: "",
                    getLong(getColumnIndexOrThrow(DatabaseContract.PostEntry.POST_ID)).toString()?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.PostEntry.TITLE))?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.PostEntry.PICTURE))?:"",
                    getString(getColumnIndexOrThrow(DatabaseContract.PostEntry.UPLOAD_TIME))?: "",
                    Profile(
                        getLong(getColumnIndexOrThrow(  DatabaseContract.FollowersProfileEntry.ID)).toString()?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.NAME))?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.DESCRIPTION))?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.PICTURE))?:"",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.POST_NUMBER))?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.FOLLOWERS_NUMBER))?: ""
                    ),
                    getInt(getColumnIndexOrThrow(DatabaseContract.PostEntry.IS_LIKED)) == 1
                ))
            }
        }
        cursor.close()
        return posts
    }

    fun getProfileList(profileId: Int?):List<Profile> {
        val db = this.writableDatabase

        var selection:String? = null
        var selectionArgs:Array<String>? = null
        if(profileId != null){
            selection = "${DatabaseContract.FollowersProfileEntry.ID} = ?"
            selectionArgs = arrayOf(profileId.toString())
        }

        val cursor = db.query(
            DatabaseContract.FollowersProfileEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val followers = mutableListOf<Profile>()

        with(cursor){
            while (moveToNext()){
                followers.add(
                    Profile(
                    getLong(getColumnIndexOrThrow(  DatabaseContract.FollowersProfileEntry.ID)).toString(),
                    getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.NAME))?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.DESCRIPTION))?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.PICTURE)),
                    getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.POST_NUMBER))?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.FOLLOWERS_NUMBER))?: ""
                )
                )
            }
        }
        cursor.close()
        return followers
    }

    //update
    fun updateProfile(profile:Profile): Boolean{
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseContract.FollowersProfileEntry.ID, profile.profileId)
            put(DatabaseContract.FollowersProfileEntry.NAME, profile.name)
            put(DatabaseContract.FollowersProfileEntry.DESCRIPTION, profile.description)
            put(DatabaseContract.FollowersProfileEntry.PICTURE, profile.picture)
            put(DatabaseContract.FollowersProfileEntry.POST_NUMBER, profile.postsNumber)
            put(DatabaseContract.FollowersProfileEntry.FOLLOWERS_NUMBER, profile.followersNumber)
        }

        val selection = "${DatabaseContract.FollowersProfileEntry.ID} = ?"
        val selectionArgs = arrayOf(profile.profileId)

        val count = db.update(
            DatabaseContract.FollowersProfileEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
        return count > 0
    }

    fun likePost(post:Post, like:Boolean? = true): Boolean{
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseContract.PostEntry.PICTURE, post.picture)
            put(DatabaseContract.PostEntry.POST_ID, post.postId)
            put(DatabaseContract.PostEntry.PROFILE_ID, post.profileId)
            put(DatabaseContract.PostEntry.TITLE, post.title)
            put(DatabaseContract.PostEntry.UPLOAD_TIME, post.uploadTime)
            if(like != null && like) {
                put(DatabaseContract.PostEntry.IS_LIKED, 1)
            } else{
                put(DatabaseContract.PostEntry.IS_LIKED, 0)
            }
        }

        val selection = "${DatabaseContract.PostEntry.POST_ID} = ?"
        val selectionArgs = arrayOf(post.postId)

        val count = db.update(
            DatabaseContract.PostEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
        return count > 0
    }

    fun getPostLike(postId:String):Boolean{
        val db = this.writableDatabase

        val selection = "${DatabaseContract.PostEntry.POST_ID} = ?"
        val selectionArgs:Array<String>? = arrayOf(postId)

        val cursor = db.query(
            DatabaseContract.PostEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var value:Boolean = false
        with(cursor){
            while (moveToNext()){
                value = getInt(getColumnIndexOrThrow(DatabaseContract.PostEntry.IS_LIKED)) == 1
            }
        }
        return value
    }

    fun getLikedPosts():List<Post>{
        val db = this.writableDatabase

        var query = """SELECT * 
            FROM ${DatabaseContract.PostEntry.TABLE_NAME} p 
            LEFT JOIN ${DatabaseContract.FollowersProfileEntry.TABLE_NAME} f 
            ON p.${DatabaseContract.PostEntry.PROFILE_ID} = f.${DatabaseContract.FollowersProfileEntry.ID}
            """

        val selectionArgs = arrayOf("1")
        query += "WHERE p.${DatabaseContract.PostEntry.IS_LIKED} = ?"

        query += "ORDER BY ${DatabaseContract.PostEntry.UPLOAD_TIME} DESC"

        val cursor = db.rawQuery(query, selectionArgs)

        val posts = mutableListOf<Post>()

        with(cursor){
            while (moveToNext()){
                posts.add(Post(
                    getLong(getColumnIndexOrThrow(DatabaseContract.PostEntry.PROFILE_ID)).toString()?: "",
                    getLong(getColumnIndexOrThrow(DatabaseContract.PostEntry.POST_ID)).toString()?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.PostEntry.TITLE))?: "",
                    getString(getColumnIndexOrThrow(DatabaseContract.PostEntry.PICTURE))?:"",
                    getString(getColumnIndexOrThrow(DatabaseContract.PostEntry.UPLOAD_TIME))?: "",
                    Profile(
                        getLong(getColumnIndexOrThrow(  DatabaseContract.FollowersProfileEntry.ID)).toString()?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.NAME))?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.DESCRIPTION))?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.PICTURE))?:"",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.POST_NUMBER))?: "",
                        getString(getColumnIndexOrThrow(DatabaseContract.FollowersProfileEntry.FOLLOWERS_NUMBER))?: ""
                    ),
                    getInt(getColumnIndexOrThrow(DatabaseContract.PostEntry.IS_LIKED)) == 1
                ))
            }
        }
        cursor.close()
        return posts
    }
}
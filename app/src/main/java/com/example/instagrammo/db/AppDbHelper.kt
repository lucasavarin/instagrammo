package com.example.instagrammo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.instagrammo.dbcontractclass.FollowerContract
import com.example.instagrammo.dbcontractclass.PostContract
import com.example.instagrammo.model.*

class AppDbHelper(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {
    private val CREATE_POST_TABLE = " CREATE TABLE ${PostContract.PostEntry.TABLE_NAME} (" +
            "${PostContract.PostEntry.COLUMN_ID} INTEGER PRIMARY KEY ," +
            "${PostContract.PostEntry.COLUMN_TITLE} TEXT, " +
            "${PostContract.PostEntry.COLUMN_PROFILE_ID} INTEGER, " +
            "${PostContract.PostEntry.COLUMN_UPLOADTIME} TEXT, " +
            " CONSTRAINT fk_profile" +
            " FOREIGN KEY (${PostContract.PostEntry.COLUMN_PROFILE_ID})" +
            " REFERENCES ${FollowerContract.FollowerEntry.TABLE_NAME} (${FollowerContract.FollowerEntry.COLUMN_ID}))"

    private val CREATE_FOLLOWER_TABLE =
        "CREATE TABLE ${FollowerContract.FollowerEntry.TABLE_NAME} (" +
                "${FollowerContract.FollowerEntry.COLUMN_ID} INTEGER PRIMARY KEY ," +
                "${FollowerContract.FollowerEntry.COLUMN_NAME} TEXT ," +
                "${FollowerContract.FollowerEntry.COLUMN_PICTURE} INTEGER ," +
                "${FollowerContract.FollowerEntry.COLUMN_DESCRIPTION} TEXT )"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_POST_TABLE)
        db?.execSQL(CREATE_FOLLOWER_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun savePostOnDb(post: List<PostDb>) {
        val db = this.writableDatabase
        post.forEach {
            val toInsert = ContentValues().apply {
                put(PostContract.PostEntry.COLUMN_ID, it.postId)
                put(PostContract.PostEntry.COLUMN_TITLE, it.title)
                put(PostContract.PostEntry.COLUMN_PROFILE_ID, it.profileId)
                put(PostContract.PostEntry.COLUMN_UPLOADTIME, it.uploadTime)

            }
            val idRow = db?.insert(PostContract.PostEntry.TABLE_NAME, null, toInsert)
            Log.d("IDROW", idRow.toString())
        }
    }

    fun saveFollowerOnDb(post: Array<Payload>) {
        val db = this.writableDatabase
        post.forEach {
            val insert = ContentValues().apply {
                put(FollowerContract.FollowerEntry.COLUMN_NAME, it.name)
                put(FollowerContract.FollowerEntry.COLUMN_ID, it.id)
                put(FollowerContract.FollowerEntry.COLUMN_DESCRIPTION, it.description)
            }
            val idRows = db?.insert(FollowerContract.FollowerEntry.TABLE_NAME, null, insert)
            Log.d("IDROWS", idRows.toString())
        }
    }

    fun getPostsFromDb(): List<Post> {
        val lista = arrayListOf<Post>()
        val db = this.readableDatabase
        val cursor =
            db?.query(PostContract.PostEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor!!.moveToNext()) {
            lista.add(
                Post(
                    cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_PROFILE_ID))
                        .toString(),
                    cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_ID))
                        .toString(),
                    cursor.getString(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_TITLE)),
                    "",
                    cursor.getString(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_UPLOADTIME)),
                    Profile(
                        cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_PROFILE_ID))
                            .toString(),
                        "", "", "", "", ""
                    )
                )
            )
        }
        cursor.close()
        return lista;
    }
    fun getFollowerFromDB() : Array<Payload>{
        val follower = arrayListOf<Payload>()
        val db = this.readableDatabase
        val cursor =
            db?.query(FollowerContract.FollowerEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor!!.moveToNext()) {
            follower.add(
                Payload(
                    cursor.getInt(cursor.getColumnIndexOrThrow(FollowerContract.FollowerEntry.COLUMN_ID)).toString(),
                    cursor.getString(cursor.getColumnIndexOrThrow(FollowerContract.FollowerEntry.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(FollowerContract.FollowerEntry.COLUMN_DESCRIPTION)),
                   ""
                )
            )

        }
        cursor.close()
        return follower.toTypedArray()
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "AppDb"
    }
}
package com.example.instagrammo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.instagrammo.dbcontractclass.Contract
import com.example.instagrammo.model.*

class AppDbHelper(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {
    private val CREATE_POST_TABLE = " CREATE TABLE ${Contract.PostEntry.TABLE_NAME} (" +
            "${Contract.PostEntry.COLUMN_ID} INTEGER PRIMARY KEY ," +
            "${Contract.PostEntry.COLUMN_TITLE} TEXT, " +
            "${Contract.PostEntry.COLUMN_PROFILE_ID} INTEGER, " +
            "${Contract.PostEntry.COLUMN_UPLOADTIME} TEXT, " +
            " CONSTRAINT fk_profile" +
            " FOREIGN KEY (${Contract.PostEntry.COLUMN_PROFILE_ID})" +
            " REFERENCES ${Contract.FollowerEntry.TABLE_NAME} (${Contract.FollowerEntry.COLUMN_ID}))"

    private val CREATE_FOLLOWER_TABLE =
        "CREATE TABLE ${Contract.FollowerEntry.TABLE_NAME} (" +
                "${Contract.FollowerEntry.COLUMN_ID} INTEGER PRIMARY KEY ," +
                "${Contract.FollowerEntry.COLUMN_NAME} TEXT ," +
                "${Contract.FollowerEntry.COLUMN_PICTURE} INTEGER ," +
                "${Contract.FollowerEntry.COLUMN_DESCRIPTION} TEXT )"


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
                put(Contract.PostEntry.COLUMN_ID, it.postId)
                put(Contract.PostEntry.COLUMN_TITLE, it.title)
                put(Contract.PostEntry.COLUMN_PROFILE_ID, it.profileId)
                put(Contract.PostEntry.COLUMN_UPLOADTIME, it.uploadTime)

            }
            val idRow = db?.insert(Contract.PostEntry.TABLE_NAME, null, toInsert)
            Log.d("IDROW", idRow.toString())
        }
    }

    fun saveFollowerOnDb(post: Array<Payload>) {
        val db = this.writableDatabase
        post.forEach {
            val insert = ContentValues().apply {
                put(Contract.FollowerEntry.COLUMN_NAME, it.name)
                put(Contract.FollowerEntry.COLUMN_ID, it.id)
                put(Contract.FollowerEntry.COLUMN_DESCRIPTION, it.description)
            }
            val idRows = db?.insert(Contract.FollowerEntry.TABLE_NAME, null, insert)
            Log.d("IDROWS", idRows.toString())
        }
    }

    fun getPostsFromDb(): List<Post> {
        val lista = arrayListOf<Post>()
        val db = this.readableDatabase
        val cursor =
            db?.query(Contract.PostEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor!!.moveToNext()) {
            lista.add(
                Post(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Contract.PostEntry.COLUMN_PROFILE_ID))
                        .toString(),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Contract.PostEntry.COLUMN_ID))
                        .toString(),
                    cursor.getString(cursor.getColumnIndexOrThrow(Contract.PostEntry.COLUMN_TITLE)),
                    "",
                    cursor.getString(cursor.getColumnIndexOrThrow(Contract.PostEntry.COLUMN_UPLOADTIME)),
                    Profile(
                        cursor.getInt(cursor.getColumnIndexOrThrow(Contract.PostEntry.COLUMN_PROFILE_ID))
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
            db?.query(Contract.FollowerEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor!!.moveToNext()) {
            follower.add(
                Payload(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Contract.FollowerEntry.COLUMN_ID)).toString(),
                    cursor.getString(cursor.getColumnIndexOrThrow(Contract.FollowerEntry.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Contract.FollowerEntry.COLUMN_DESCRIPTION)),
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
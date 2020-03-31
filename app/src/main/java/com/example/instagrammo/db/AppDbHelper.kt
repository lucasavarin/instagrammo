package com.example.instagrammo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.instagrammo.dbcontractclass.PostContract
import com.example.instagrammo.model.Post
import com.example.instagrammo.model.PostDb
import com.example.instagrammo.model.Profile

class AppDbHelper(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {
    private val CREATE_POST_TABLE = " CREATE TABLE ${PostContract.PostEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY ," +
            "${PostContract.PostEntry.COLUMN_TITLE} TEXT ," +
            "${PostContract.PostEntry.COLUMN_PROFILE_ID} INTEGER ," +
            "${PostContract.PostEntry.COLUMN_UPLOADTIME} TEXT " +
            " )"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_POST_TABLE)

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

    fun getPostsFromDb(): List<Post> {
        val lista = arrayListOf<Post>()
        val db = this.readableDatabase
        val cursor =
            db?.query(PostContract.PostEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor!!.moveToNext()) {
            lista.add(
                Post(
                    cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_PROFILE_ID)).toString(),
                    cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_ID)).toString(),
                    cursor.getString(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_TITLE)),
                    "",
                    cursor.getString(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_UPLOADTIME)),
                    Profile(
                        cursor.getInt(cursor.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_PROFILE_ID)).toString(),
                        "", "", "", "", ""
                    )

                )
            )
        }
        return lista;
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "AppDb"
    }
}
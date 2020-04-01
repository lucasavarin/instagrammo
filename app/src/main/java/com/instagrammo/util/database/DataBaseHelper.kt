package com.instagrammo.util.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.instagrammo.bean.buissnes.HomePayloadPostBean
import com.instagrammo.bean.buissnes.HomeProfilePostBean
import com.instagrammo.bean.buissnes.HomeUserResponseBean

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun insertPostData(response: List<HomePayloadPostBean>){
        val db = this.writableDatabase
        SQL_DELETE_ENTRIES

        response.forEach {

            val values = ContentValues().apply {
                    put(BaseColumns._ID, it.postId)
                    put(DataBaseContract.Post.COLUMN_PROFILE_ID, it.HomeProfilePostBean.profileId)
                    put(DataBaseContract.Post.COLUMN_TITLE_POSTTIME, it.uploadTime)
                    put(DataBaseContract.Post.COLUMN_TITLE_USER, it.title)
                    put(DataBaseContract.Post.COLUMN_TITLE_DESC, it.HomeProfilePostBean.description)
                }
                  db?.insert(DataBaseContract.Post.TABLE_NAME, null, values)
            }
        }

    fun insertFollowerData(response : List<HomeUserResponseBean>){
        val db = this.writableDatabase
        SQL_DELETE_FOLLOWER

        response.forEach {

            val values = ContentValues().apply {
                put(DataBaseContract.Follower.COLUMN_ID, it.id.toInt())
                put(DataBaseContract.Follower.COLUMN_NAME, it.name)
                put(DataBaseContract.Follower.COLUMN_DESCRIPTION, it.description)
            }
            db?.insert(DataBaseContract.Follower.TABLE_NAME_FOLLOWER,null, values)
        }

    }

    fun readPostData() : List<HomePayloadPostBean>{
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            DataBaseContract.Post.COLUMN_TITLE_DESC,
            DataBaseContract.Post.COLUMN_TITLE_POSTTIME,
            DataBaseContract.Post.COLUMN_TITLE_USER,
            DataBaseContract.Post.COLUMN_PROFILE_ID)

        val sortOrder = "${DataBaseContract.Post.COLUMN_TITLE_POSTTIME} DESC"

        val cursor = db.query(
            DataBaseContract.Post.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val items = arrayListOf<HomePayloadPostBean>()
        with(cursor){
            while (moveToNext()){
                items.add(
                    HomePayloadPostBean((
                            cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_PROFILE_ID))),
                        cursor.getString(getColumnIndexOrThrow(BaseColumns._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_USER)),
                        "",
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_POSTTIME)),
                        HomeProfilePostBean(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_PROFILE_ID))
                            ,"",cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_DESC)),"","",""
                        )
                    )
                )
            }
        }
        cursor.close()

        return items
    }

    fun readFollowerData() : List<HomeUserResponseBean>{
        val db = this.readableDatabase
        val projection = arrayOf(DataBaseContract.Follower.COLUMN_ID,
            DataBaseContract.Follower.COLUMN_NAME,
            DataBaseContract.Follower.COLUMN_DESCRIPTION)

        val cursor = db.query(
            DataBaseContract.Follower.TABLE_NAME_FOLLOWER,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val items = arrayListOf<HomeUserResponseBean>()
        with(cursor) {
            while (moveToNext()) {
                items.add((HomeUserResponseBean(
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Follower.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Follower.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Follower.COLUMN_DESCRIPTION)),
                    ""
                )))

            }
        }

        cursor.close()

        return items

    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL(SQL_CREATE_FOLLOWER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        db.execSQL(SQL_DELETE_FOLLOWER)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "database.db"
        const val DATABASE_VERSION = 1
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DataBaseContract.Post.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${DataBaseContract.Post.COLUMN_TITLE_USER} TEXT," +
                    "${DataBaseContract.Post.COLUMN_TITLE_POSTTIME} TEXT," +
                    "${DataBaseContract.Post.COLUMN_TITLE_DESC} TEXT," +
                    "${DataBaseContract.Post.COLUMN_PROFILE_ID} TEXT," +
                    " CONSTRAINT fk_profile" +
                    " FOREIGN KEY (${DataBaseContract.Post.COLUMN_PROFILE_ID})" +
                    " REFERENCES ${DataBaseContract.Follower.TABLE_NAME_FOLLOWER} (${DataBaseContract.Follower.COLUMN_ID}))"

        private const val SQL_CREATE_FOLLOWER =
            "CREATE TABLE ${DataBaseContract.Follower.TABLE_NAME_FOLLOWER} (" +
                    "${DataBaseContract.Follower.COLUMN_ID} INTEGER PRIMARY KEY," +
                    "${DataBaseContract.Follower.COLUMN_NAME} TEXT, " +
                    "${DataBaseContract.Follower.COLUMN_DESCRIPTION} TEXT)"

        private const val SQL_DELETE_FOLLOWER =
            "DROP TABLE IF EXISTS ${DataBaseContract.Follower.TABLE_NAME_FOLLOWER}"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${DataBaseContract.Post.TABLE_NAME}"
    }


}
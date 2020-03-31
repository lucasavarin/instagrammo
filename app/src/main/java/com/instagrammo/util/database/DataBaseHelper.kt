package com.instagrammo.util.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.instagrammo.bean.buissnes.HomePayloadPostBean
import com.instagrammo.bean.buissnes.ProfilePostBean
import retrofit2.Response

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //create
    fun insertPostData(response: List<HomePayloadPostBean>){
        val db = this.writableDatabase


        val values = ContentValues().apply {
            response.forEach {
                put(DataBaseContract.Post.COLUMN_TITLE_POSTTIME, it.uploadTime)
                put(DataBaseContract.Post.COLUMN_TITLE_USER, it.HomeProfilePostBean.name)
                put(DataBaseContract.Post.COLUMN_TITLE_DESC, it.HomeProfilePostBean.description)
            }

        }
        val newRowId = db?.insert(DataBaseContract.Post.TABLE_NAME, null, values)

    }

    //read
    fun readPostData(){
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID, DataBaseContract.Post.COLUMN_TITLE_DESC, DataBaseContract.Post.COLUMN_TITLE_POSTTIME, DataBaseContract.Post.COLUMN_TITLE_USER)
    //    val selection = "${DataBaseContract.Post.COLUMN_TITLE_USER} = ?"
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

        val items = mutableListOf<String>()
        with(cursor){
            while (moveToNext()){
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID)).toString()
                val itemDesc = getString(getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_DESC))
                val itemPostTime = getString(getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_POSTTIME))
                val itemUser = getString(getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_USER))
                items.add(itemId)
                items.add(itemDesc)
                items.add(itemPostTime)
                items.add(itemUser)
            }
        }
        cursor.close()
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
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
                    "${DataBaseContract.Post.COLUMN_TITLE_DESC} TEXT)"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${DataBaseContract.Post.TABLE_NAME}"
    }


}
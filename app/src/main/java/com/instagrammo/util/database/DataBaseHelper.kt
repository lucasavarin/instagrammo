package com.instagrammo.util.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.instagrammo.bean.buissnes.HomePayloadPostBean
import com.instagrammo.bean.buissnes.HomeProfilePostBean
import com.instagrammo.bean.buissnes.ProfilePostBean
import com.instagrammo.util.retrofit.Session
import retrofit2.Response

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //create
    fun insertPostData(response: List<HomePayloadPostBean>){
        val db = this.writableDatabase

        response.forEach {

            val values = ContentValues().apply {
                    put(DataBaseContract.Post.COLUMN_PROFILE_ID, it.HomeProfilePostBean.profileId)
                    put(DataBaseContract.Post.COLUMN_TITLE_POSTTIME, it.uploadTime)
                    put(DataBaseContract.Post.COLUMN_TITLE_USER, it.title)
                    put(DataBaseContract.Post.COLUMN_TITLE_DESC, it.HomeProfilePostBean.description)
                }
                  db?.insert(DataBaseContract.Post.TABLE_NAME, null, values)
            }
        }

    //read
    fun readPostData() : List<HomePayloadPostBean>{
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
            DataBaseContract.Post.COLUMN_TITLE_DESC,
            DataBaseContract.Post.COLUMN_TITLE_POSTTIME,
            DataBaseContract.Post.COLUMN_TITLE_USER,
            DataBaseContract.Post.COLUMN_PROFILE_ID)

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

        val items = arrayListOf<HomePayloadPostBean>()
        with(cursor){
            while (moveToNext()){
                items.add(
                    HomePayloadPostBean((
                            cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_PROFILE_ID))),
                        cursor.getString(getColumnIndexOrThrow(BaseColumns._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_USER)),
                        "https://picsum.photos/id/12/400/400",
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_POSTTIME)),
                        HomeProfilePostBean(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_PROFILE_ID))
                            ,"",cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.Post.COLUMN_TITLE_DESC)),"https://picsum.photos/id/12/400/400","",""
                        )
                    )
                )
            }
        }
        cursor.close()

        return items
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
                    "${DataBaseContract.Post.COLUMN_TITLE_DESC} TEXT," +
                    "${DataBaseContract.Post.COLUMN_PROFILE_ID} TEXT)"


        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${DataBaseContract.Post.TABLE_NAME}"
    }


}
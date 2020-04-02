package com.lynxspa.instagrammo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(Contract.CREATE_SQL_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        onCreate(p0)
    }

    fun insert(appDataBean: AppDataBean){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Contract.COLUMN_TITLE, appDataBean.titolo)
            put(Contract.COLUMN_DESCRIPTION, appDataBean.descrizione)
        }
        db.insert(Contract.TABLE_NAME, null, values)
        db.close()
    }

    fun query() : List<AppDataBean>{
        val db = this.readableDatabase
        val cursor =  db.query(Contract.TABLE_NAME, null, null, null, null, null, null)
        val lista : ArrayList<AppDataBean>  = arrayListOf()
        while (cursor.moveToNext()){
            val appdataBean = AppDataBean(
            cursor.getString(cursor.getColumnIndexOrThrow(Contract.COLUMN_TITLE)),
            cursor.getString(cursor.getColumnIndexOrThrow(Contract.COLUMN_DESCRIPTION))
                )
            lista.add(appdataBean)
        }
        cursor.close()
        db.close()
        return lista
    }
    companion object{
        var DATABASE_NAME = "AppDb"
        var DATABASE_VERSION = 1
    }
}
package com.lynxspa.instagrammo.db

import android.provider.BaseColumns

object Contract : BaseColumns{
    val TABLE_NAME = "tabella"
    val COLUMN_DESCRIPTION = "descrizione"
    val COLUMN_TITLE = "titolo"

    val CREATE_SQL_TABLE = """
        CREATE TABLE $TABLE_NAME (
        ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, 
        $COLUMN_TITLE TEXT,
        $COLUMN_DESCRIPTION TEXT
        )
    """.trimIndent()
}
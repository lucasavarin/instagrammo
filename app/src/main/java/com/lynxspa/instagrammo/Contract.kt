package com.lynxspa.instagrammo

import android.provider.BaseColumns

object Contract : BaseColumns{
    val TABLE_NAME = "tabella"
    val COLUMN_DESCRIPTION = "descrizione"
    val COLUMN_TITLE = "titolo"

    val CREATE_SQL_TABLE = """
        CREATE TABLE ${Contract.TABLE_NAME} (
        ${BaseColumns._ID} INT PRIMARY KEY AUTOINCREMENT, 
        ${Contract.COLUMN_TITLE} TEXT,
        ${Contract.COLUMN_DESCRIPTION} TEXT
        )
    """.trimIndent()
}
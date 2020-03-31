package com.instagrammo.util.database

import android.provider.BaseColumns

object DataBaseContract {

    object Post : BaseColumns {

        const val TABLE_NAME = "post"
        const val COLUMN_TITLE_USER = "user"
        const val COLUMN_TITLE_POSTTIME = "postTime"
        const val COLUMN_TITLE_DESC = "description"
    }
}
package com.example.instagrammo.db

import android.provider.BaseColumns

object ContractPost {

    object PostEntry : BaseColumns{
        const val TABLE_NAME = "Post"
        const val COLUMN_TITLE = "title"
        const val COLUMN_UPLOAD = "upload_time"
        const val COLUMN_ID = "post_id"
        const val COLUMN_PROFILE_ID = "profile_id"
    }
}
package com.example.instagrammo.dbcontractclass

import android.provider.BaseColumns

object PostContract {


    object PostEntry : BaseColumns{
        const val TABLE_NAME = "Post"
        const val COLUMN_TITLE = "title"
        const val COLUMN_PROFILE_ID ="profileId"
        const val COLUMN_ID ="postId"
        const val COLUMN_UPLOADTIME="uploadTime"

    }
}
package com.example.instagrammo.dbcontractclass

import android.provider.BaseColumns

object FollowerContract {

    object FollowerEntry : BaseColumns {
        const val TABLE_NAME = "Follower"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PICTURE = "picture"
        const val COLUMN_DESCRIPTION = "description"

    }
}
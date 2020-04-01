package com.example.instagrammo.db

import android.provider.BaseColumns

object ContractFollower {

    object FollowerEntry : BaseColumns{
        const val TABLE_NAME = "Followers"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PICTURE = "picture"
    }
}
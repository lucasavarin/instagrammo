package com.example.instagrammo.db.dbcontractclass

import android.provider.BaseColumns

object Contract {

    object PostEntry : BaseColumns{
        const val TABLE_NAME = "Post"
        const val COLUMN_TITLE = "title"
        const val COLUMN_PROFILE_ID ="profileId"
        const val COLUMN_ID ="postId"
        const val COLUMN_UPLOADTIME="uploadTime"

    }

    object FollowerEntry : BaseColumns {
        const val TABLE_NAME = "Follower"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PICTURE = "picture"
        const val COLUMN_DESCRIPTION = "description"

    }

}
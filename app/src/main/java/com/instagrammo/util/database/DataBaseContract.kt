package com.instagrammo.util.database

import android.provider.BaseColumns

object DataBaseContract {

    object Post : BaseColumns {

        const val TABLE_NAME = "post"
        const val COLUMN_TITLE_USER = "title"
        const val COLUMN_TITLE_POSTTIME = "uploadTime"
        const val COLUMN_TITLE_DESC = "description"
        const val COLUMN_PROFILE_ID = "profileId"
  }

    object Follower : BaseColumns {
        const val TABLE_NAME_FOLLOWER = "follower"
        const val COLUMN_ID ="idFollower"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
    }
}
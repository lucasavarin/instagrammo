package com.mst.instagrammo.database

import android.provider.BaseColumns

object DBContract {

    object PostEntry: BaseColumns {
        const val TABLE_NAME = "Post"
        const val COLUMN_NAME_ID = "postId"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_UPLOADTIME = "uploadTime"
        const val COLUMN_NAME_PROFILEID = "profileId"
    }

    object FollowerEntry: BaseColumns {
        const val TABLE_NAME = "Follower"
        const val COLUMN_NAME_ID = "followerId"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PICTURE = "picture"
    }
}
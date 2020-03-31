package com.example.instagrammo.util

import android.provider.BaseColumns

object DatabaseContract {
    object PostEntry: BaseColumns {
        const val TABLE_NAME = "posts"
        const val PROFILE_ID = "profile_id"
        const val POST_ID = "post_id"
        const val TITLE = "title"
        const val PICTURE = "picture"
        const val UPLOAD_TIME = "upload_time"
    }

    object FollowersEntry: BaseColumns {
        const val TABLE_NAME = "followers"
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val PICTURE = "picture"
    }
}
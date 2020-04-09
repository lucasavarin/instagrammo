package com.example.instagrammo.database

import android.provider.BaseColumns

object DatabaseContract {
    object PostEntry: BaseColumns {
        const val TABLE_NAME = "posts"
        const val PROFILE_ID = "post_profile_id"
        const val POST_ID = "post_id"
        const val TITLE = "post_title"
        const val PICTURE = "post_picture"
        const val UPLOAD_TIME = "post_upload_time"
        const val IS_LIKED = "post_is_liked"
    }

    object FollowersProfileEntry: BaseColumns {
        const val TABLE_NAME = "followers"
        const val ID = "follower_id"
        const val NAME = "follower_name"
        const val DESCRIPTION = "follower_description"
        const val PICTURE = "follower_picture"
        const val POST_NUMBER = "follower_post_number"
        const val FOLLOWERS_NUMBER = "followers_number"
    }
}
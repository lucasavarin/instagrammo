package thushyanthan.scott.javalynx.instagrammo.util.database

import android.provider.BaseColumns

object FeedReaderContract {
    object PostEntry :BaseColumns{
        const val TABLE_NAME = "post"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_POST_ID = "postId"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_UPLOAD_TIME = "uploadTime"
    }

    object FollowersEntry :BaseColumns{
        const val TABLE_NAME = "followers"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_FOLLOWERS_ID = "followersId"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PICTURE = "picture"

    }

    object ProfileEntry : BaseColumns {
        const val TABLE_NAME = "profile"
        const val COLUMN_NAME_PROFILE_ID = "profileId"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PICTURE = "picture"
        const val COLUMN_NAME_FOLLOWERS_NUMBER = "followersNumber"
        const val COLUMN_NAME_POSTS_NUMBER = "postsNumber"

    }
}
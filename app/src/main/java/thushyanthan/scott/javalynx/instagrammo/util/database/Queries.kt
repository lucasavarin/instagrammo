package thushyanthan.scott.javalynx.instagrammo.util.database

object Queries {
     const val SQL_CREATE_ENTRIES_POSTS =
        "CREATE TABLE IF NOT EXISTS ${FeedReaderContract.PostEntry.TABLE_NAME} ("+
                "${FeedReaderContract.PostEntry.COLUMN_NAME_POST_ID} INTEGER PRIMARY KEY,"+
                "${FeedReaderContract.PostEntry.COLUMN_NAME_TITLE} TEXT,"+
                "${FeedReaderContract.PostEntry.COLUMN_NAME_PICTURE} TEXT,"+
                "${FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME} TEXT,"+
                "${FeedReaderContract.PostEntry.COLUMN_NAME_PROFILE_ID} INTEGER,"+
                "FOREIGN KEY (${FeedReaderContract.PostEntry.COLUMN_NAME_PROFILE_ID}) REFERENCES ${FeedReaderContract.ProfileEntry.TABLE_NAME} (${FeedReaderContract.ProfileEntry.COLUMN_NAME_PROFILE_ID}))"

     const val SQL_CREATE_ENTRIES_FOLLOWERS =
        "CREATE TABLE IF NOT EXISTS ${FeedReaderContract.FollowersEntry.TABLE_NAME}("+
                "${FeedReaderContract.FollowersEntry.COLUMN_NAME_FOLLOWERS_ID} INTEGER PRIMARY KEY,"+
                "${FeedReaderContract.FollowersEntry.COLUMN_NAME_NAME} TEXT,"+
                "${FeedReaderContract.FollowersEntry.COLUMN_NAME_DESCRIPTION} TEXT,"+
                "${FeedReaderContract.FollowersEntry.COLUMN_NAME_PICTURE} TEXT)"

     const val SQL_CREATE_ENTRIES_PROFILE =
        "CREATE TABLE IF NOT EXISTS ${FeedReaderContract.ProfileEntry.TABLE_NAME} ("+
                "${FeedReaderContract.ProfileEntry.COLUMN_NAME_PROFILE_ID} INTEGER PRIMARY KEY,"+
                "${FeedReaderContract.ProfileEntry.COLUMN_NAME_NAME} TEXT,"+
                "${FeedReaderContract.ProfileEntry.COLUMN_NAME_DESCRIPTION} TEXT,"+
                "${FeedReaderContract.ProfileEntry.COLUMN_NAME_PICTURE} TEXT,"+
                "${FeedReaderContract.ProfileEntry.COLUMN_NAME_FOLLOWERS_NUMBER} TEXT,"+
                "${FeedReaderContract.ProfileEntry.COLUMN_NAME_POSTS_NUMBER} TEXT)"

    const val SQL_DELETE_ENTRIES_POSTS = "DROP TABLE IF EXISTS ${FeedReaderContract.PostEntry.TABLE_NAME}"
    const val SQL_DELETE_ENTRIES_FOLLOWERS = "DROP TABLE IF EXISTS ${FeedReaderContract.FollowersEntry.TABLE_NAME}"
    const val SQL_DELETE_ENTRIES_PROFILE = "DROP TABLE IF EXISTS ${FeedReaderContract.ProfileEntry.TABLE_NAME}"





}
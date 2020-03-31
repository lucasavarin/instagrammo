package thushyanthan.scott.javalynx.instagrammo.fragments

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.*
import thushyanthan.scott.javalynx.instagrammo.adapter.FollowerAdapter
import thushyanthan.scott.javalynx.instagrammo.adapter.HomeAdapter
import thushyanthan.scott.javalynx.instagrammo.util.database.FeedReaderContract
import thushyanthan.scott.javalynx.instagrammo.util.rest.FollowerPayload
import thushyanthan.scott.javalynx.instagrammo.util.rest.FollowerResponse
import thushyanthan.scott.javalynx.instagrammo.util.rest.PostPayload
import thushyanthan.scott.javalynx.instagrammo.util.rest.PostsResponse
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.dbHelper
import thushyanthan.scott.javalynx.instagrammo.util.rest.*
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs
import java.util.ArrayList

class HomeFragment : Fragment() {
    var posts: List<PostPayload> = ArrayList()
    var followers: List<FollowerPayload> = ArrayList()
    private lateinit var linearLayoutManagerFollowers: LinearLayoutManager
    private lateinit var linearLayoutManagerPosts: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPosts()
        getFollowers()

        linearLayoutManagerPosts = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        linearLayoutManagerFollowers = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        homePostsListLayout.layoutManager = linearLayoutManagerPosts
        homeFollowersListLayout.layoutManager = linearLayoutManagerFollowers
    }

    fun readPostsFromDb(): List<PostPayload> {
        val db = dbHelper.readableDatabase

        val query =
            "SELECT * FROM ${FeedReaderContract.PostEntry.TABLE_NAME} INNER JOIN ${FeedReaderContract.ProfileEntry.TABLE_NAME}" +
                    " on ${FeedReaderContract.PostEntry.COLUMN_NAME_PROFILE_ID} = ${FeedReaderContract.ProfileEntry.COLUMN_NAME_PROFILE_ID}"

        val cursor = db.rawQuery(query, null)


        val postList = arrayListOf<PostPayload>()

        with(cursor) {
            while (moveToNext()) {
                postList.add(
                    PostPayload(
                        getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_PROFILE_ID)),
                        getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_POST_ID)),
                        getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_TITLE)),
                        getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_PICTURE)),
                        getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME)),
                        PostProfileBean(
                            getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_PROFILE_ID)),
                            getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_NAME)),
                            getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_DESCRIPTION)),
                            getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_PICTURE)),
                            getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_FOLLOWERS_NUMBER)),
                            getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_POSTS_NUMBER))
                        )
                    )
                )
            }
        }
        cursor.close()
        return postList
    }

    fun getPosts() {
        val postsCall = ApiClient.getClient.requestPosts()

        postsCall.enqueue(object : Callback<PostsResponse> {
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                posts = readPostsFromDb()
                homePostsListLayout.adapter =
                    HomeAdapter(posts, context!!)
                homePostsListLayout.adapter?.notifyDataSetChanged()
                Toast.makeText(activity, "Update Posts from server failed", Toast.LENGTH_SHORT)
                    .show()
            }


            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if (response.isSuccessful) {
                    val resultBody = response.body()!!
                    if (resultBody.result) {
                        posts = resultBody.payload
                        homePostsListLayout.adapter =
                            HomeAdapter(
                                posts,
                                context!!
                            )
                        homePostsListLayout.adapter?.notifyDataSetChanged()
                        createPosts()
                    }
                } else
                    Toast.makeText(activity, "Error2Posts", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun readFollowersFromDb() : List<FollowerPayload>{
        val db = dbHelper.readableDatabase


        val cursor = db.query(
            FeedReaderContract.FollowersEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)

        val followerList = arrayListOf<FollowerPayload>()
        with(cursor){
            while (moveToNext()){
                followerList.add(
                    FollowerPayload(
                        getString(getColumnIndexOrThrow(FeedReaderContract.FollowersEntry.COLUMN_NAME_FOLLOWERS_ID)),
                                getString(getColumnIndexOrThrow(FeedReaderContract.FollowersEntry.COLUMN_NAME_NAME)),
                        getString(getColumnIndexOrThrow(FeedReaderContract.FollowersEntry.COLUMN_NAME_DESCRIPTION)),
                        getString(getColumnIndexOrThrow(FeedReaderContract.FollowersEntry.COLUMN_NAME_PICTURE))
                    )
                )
            }
        }
        cursor.close()
        return followerList
    }

    fun getFollowers() {
        val followersCall = ApiClient.getClient.requestFollowers(prefs.profiloUtente)
        followersCall.enqueue(object : Callback<FollowerResponse> {
            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                followers = readFollowersFromDb()
                homeFollowersListLayout.adapter= FollowerAdapter(
                    followers,
                    context!!
                )
                homeFollowersListLayout.adapter?.notifyDataSetChanged()
                Toast.makeText(activity, "Error1Followers", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                if (response.isSuccessful) {
                    val resultBody = response.body()!!
                    if (resultBody.result) {
                        followers = resultBody.payload
                        homeFollowersListLayout.adapter =
                            FollowerAdapter(
                                followers,
                                context!!
                            )
                        homeFollowersListLayout.adapter?.notifyDataSetChanged()
                        createFollowers()
                    }
                } else
                    Toast.makeText(activity, "Error2Followers", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun createFollowers() {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {

            for (i in followers){
                put(FeedReaderContract.FollowersEntry.COLUMN_NAME_NAME,i.name)
                put(FeedReaderContract.FollowersEntry.COLUMN_NAME_PICTURE,i.picture)
                put(FeedReaderContract.FollowersEntry.COLUMN_NAME_DESCRIPTION,i.description)

            }
        }

        val newRowFollowerId = db?.insert(FeedReaderContract.FollowersEntry.TABLE_NAME, null, values)
    }

    fun createPosts() {
        val db = dbHelper.writableDatabase

        val valuesPosts = ContentValues().apply {
            for (i in posts) {
                //put(FeedReaderContract.PostEntry.COLUMN_NAME_POST_ID, i.postId.toInt())
                //put(FeedReaderContract.PostEntry.COLUMN_NAME_PROFILE_ID, i.profileId.toInt())
                put(FeedReaderContract.PostEntry.COLUMN_NAME_TITLE, i.title)
                put(FeedReaderContract.PostEntry.COLUMN_NAME_PICTURE, i.picture)
                put(FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME, i.uploadTime)

            }

        }

        val valuesProfiles = ContentValues().apply {
            for (i in posts) {
                //put(FeedReaderContract.ProfileEntry.COLUMN_NAME_PROFILE_ID, i.profile.profileId)
                put(FeedReaderContract.ProfileEntry.COLUMN_NAME_NAME, i.profile.name)
                put(FeedReaderContract.ProfileEntry.COLUMN_NAME_DESCRIPTION, i.profile.description)
                put(FeedReaderContract.ProfileEntry.COLUMN_NAME_PICTURE, i.profile.profilePicture)
                put(
                    FeedReaderContract.ProfileEntry.COLUMN_NAME_FOLLOWERS_NUMBER,
                    i.profile.followersNumber
                )
                put(FeedReaderContract.ProfileEntry.COLUMN_NAME_POSTS_NUMBER, i.profile.postsNumber)

            }
        }

        val newRowPostId = db?.insert(FeedReaderContract.PostEntry.TABLE_NAME, null, valuesPosts)

        val newRowProfileId = db?.insert(FeedReaderContract.ProfileEntry.TABLE_NAME, null, valuesProfiles)

    }


}
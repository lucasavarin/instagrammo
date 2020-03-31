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

        val projection = arrayOf(
            FeedReaderContract.ProfileEntry.COLUMN_NAME_PICTURE,
            FeedReaderContract.PostEntry.COLUMN_NAME_PICTURE,
            FeedReaderContract.PostEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME,
            FeedReaderContract.ProfileEntry.COLUMN_NAME_NAME
        )

        val sortOrder = "${FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME} DESC"

        val cursor = db.query(
            FeedReaderContract.PostEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        /* val itemProfPics = mutableListOf<String>()
         val itemPostPics = mutableListOf<String>()
         val itemPostTitles = mutableListOf<String>()
         val itemPostUploadTimes= mutableListOf<String>()
         val itemProfNames = mutableListOf<String>()*/

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

                /*val itemProfPic = getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_PICTURE))
                itemProfPics.add(itemProfPic)
                val itemPostPic = getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_PICTURE))
                itemPostPics.add(itemPostPic)
                val itemPostTitle = getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_TITLE))
                itemPostTitles.add(itemPostTitle)
                val itemPostUploadTime = getString(getColumnIndexOrThrow(FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME))
                itemPostUploadTimes.add(itemPostUploadTime)
                val itemProfName = getString(getColumnIndexOrThrow(FeedReaderContract.ProfileEntry.COLUMN_NAME_NAME))
                itemProfNames.add(itemProfName)*/
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

    fun getFollowers() {
        val followersCall = ApiClient.getClient.requestFollowers(prefs.profiloUtente)
        followersCall.enqueue(object : Callback<FollowerResponse> {
            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
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

    fun createFollowers(){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply{
            put(FeedReaderContract.FollowersEntry.COLUMN_NAME_FOLLOWERS_ID, followers.forEach{it.id}.toString())
            put(FeedReaderContract.FollowersEntry.COLUMN_NAME_NAME, followers.forEach{it.name}.toString())
            put(FeedReaderContract.FollowersEntry.COLUMN_NAME_DESCRIPTION, followers.forEach{it.description}.toString())
            put(FeedReaderContract.FollowersEntry.COLUMN_NAME_PICTURE, followers.forEach{it.picture}.toString())
        }

        val newRowFollowerId = db?.insert(FeedReaderContract.FollowersEntry.TABLE_NAME, null, values)
    }

    fun createPosts(){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(FeedReaderContract.PostEntry.COLUMN_NAME_POST_ID, posts.forEach{it.postId}.toString())
            put(FeedReaderContract.PostEntry.COLUMN_NAME_PROFILE_ID, posts.forEach{it.profileId}.toString())
            put(FeedReaderContract.PostEntry.COLUMN_NAME_TITLE, posts.forEach{it.title}.toString())
            put(FeedReaderContract.PostEntry.COLUMN_NAME_PICTURE, posts.forEach{it.picture}.toString())
            put(FeedReaderContract.PostEntry.COLUMN_NAME_UPLOAD_TIME, posts.forEach{it.uploadTime}.toString())
        }

        val newRowPostId = db?.insert(FeedReaderContract.PostEntry.TABLE_NAME, null,values)
    }










}
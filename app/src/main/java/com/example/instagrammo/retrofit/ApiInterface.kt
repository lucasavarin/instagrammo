package com.example.instagrammo.retrofit

import com.example.instagrammo.beans.request.AuthRequestREST
import com.example.instagrammo.beans.business.AuthResponse
import com.example.instagrammo.beans.request.ProfilePutREST
import com.example.instagrammo.beans.request.UpdatePostREST
import com.example.instagrammo.beans.response.*
import com.example.instagrammo.util.Session
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(
        @Body authRequest: AuthRequestREST
    ): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getFollowers(
        @Path("profiloUtente") profileId:Int = Session.profileId
    ):Call<FollowersWrapperREST>

    @GET("posts.php")
    fun getPosts():Call<PostsWrapperResponseREST>

    @GET("profiles.php/{profiloUtente}")
    fun getProfileSingle(
        @Path("profiloUtente") profileId: Int
    ):Call<ProfileWrapperResponseREST>

    @GET("profiles.php")
    fun getProfileList():Call<ProfileWrapperResponseREST>

    @GET("posts.php/{profiloUtente}")
    fun getProfilePosts(
        @Path("profiloUtente") profileId: Int
    ):Call<ProfilePostResponseWrapperREST>

    @PUT("profiles.php")
    fun updateProfile(
        @Body profilePut: ProfilePutREST
    ):Call<ProfilePutResponseREST>

    @POST("posts.php")
    fun updatePost(
        @Body post: UpdatePostREST
    ):Call<PostPutResponseREST>

    @GET("posts_number.php")
    fun getPostNumber() : Call<NotificationResponseREST>
}
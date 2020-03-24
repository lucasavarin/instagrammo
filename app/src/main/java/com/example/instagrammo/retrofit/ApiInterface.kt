package com.example.instagrammo.retrofit


import com.example.instagrammo.model.*
import com.example.instagrammo.model.HomeWrapperPostBean
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface{

    @POST("auth.php")
    fun auth(
        @Body user: User
    ): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getStoriesList(@Path("profiloUtente") id:String) : Call<StoriesResponse>

    @GET(value = "posts.php")
    fun getFollowerPost() : Call<HomeWrapperPostBean>

    @GET(value = "posts.php/{profiloUtente}")
    fun getProfilePost(@Path("profiloUtente") profileId: Int = Session.profileId):Call<ProfilePostBean>

    @PUT("profiles.php")
    fun saveUserData(@Body data : SaveDataBean) : Call<SaveUserDataResponseBean>

    @GET("profiles.php/{profiloUtente}")
    fun getProfile(@Path("profiloUtente") profileId: Int = Session.profileId):Call<ProfileWrapperRest>

    @GET(value="list")
    fun getPosts(@Query("page") page:String) : Call<List<AddPostResponseBean>>

    @POST(value = "posts.php")
    fun createPost(@Body createPostRequest : AddPostInfo) : Call<AddPostResult>

    @GET(value="posts_number.php")
    fun getNPosts() : Call<PostsNumberResponse>

}
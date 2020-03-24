package com.example.instagrammo.retrofit

import com.example.instagrammo.data_class.ResponseStories
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.request.CreatePostRequestBean
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.beans.response.CreatePostResponseBean
import com.example.instagrammo.beans.response.HomeWrapperPostBean
import com.example.instagrammo.beans.response.ProfileWrapperBean
import com.example.instagrammo.data_class.EditProfileBean
import com.example.instagrammo.data_class.SaveProfileBean
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST(value = "auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET(value = "followers.php/{profiloUtente}")
    fun getStoriesList(@Path ("profiloUtente") id : String): Call<ResponseStories>

    @GET(value = "posts.php")
    fun getPosts(): Call<HomeWrapperPostBean>

    @GET(value = "profiles.php/{profiloUtente}")
    fun getProfile(@Path("profiloUtente") profiloUtente: String = Session.profileId) : Call<ProfileWrapperBean>

    @PUT(value = "profiles.php")
    fun saveProfile(@Body editProfileRequest : EditProfileBean) : Call<SaveProfileBean>

    @POST(value = "posts.php")
    fun addPost(@Body createPostRequestBean: CreatePostRequestBean) : Call<CreatePostResponseBean>
}
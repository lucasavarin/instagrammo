package com.example.util.retrofit

import com.example.bean.buissnes.HomeWrapperPostBean
import com.example.bean.buissnes.HomeWrapperResponse
import com.example.bean.buissnes.ProfileImgWrapper
import com.example.bean.rest.response.AuthResponse
import com.example.bean.buissnes.ProfileWrapperResponse
import com.example.bean.rest.request.AuthRequest as AuthRequest1
import retrofit2.Call
import retrofit2.http.*

interface APiInterface {

    @POST(value = "auth.php")
    fun getUser(@Body authRequest : AuthRequest1) : Call<AuthResponse>

    @GET(value = "followers.php/{profiloUtente}")
    fun getFollowers (

        @Path("profiloUtente")
        profiloUtente : String = Session.profileId

    ) : Call<HomeWrapperResponse>

    @GET(value = "profiles.php/{profiloUtente}")
    fun getProfile (

        @Path("profiloUtente")
        profiloUtente : String

    ) : Call<ProfileWrapperResponse>


    @GET(value = "posts.php")
    fun getFollowerPost() : Call<HomeWrapperPostBean>

    @GET(value = "posts.php")
    fun getPosts (
        @Query("from")  from: String,
        @Query("elements")  elements: String
    ) : Call<ProfileImgWrapper>
}
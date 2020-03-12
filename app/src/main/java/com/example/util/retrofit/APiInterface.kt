package com.example.util.retrofit

import com.example.bean.buissnes.HomeWrapperPostBean
import com.example.bean.buissnes.HomeWrapperResponse
import com.example.bean.rest.response.AuthResponse
import com.example.bean.rest.request.AuthRequest as AuthRequest1
import retrofit2.Call
import retrofit2.http.*

interface APiInterface {

    @POST(value = "auth.php")
    fun getUser(@Body authRequest : AuthRequest1) : Call<AuthResponse>

    @GET(value = "followers.php/{profiloUtente}")
    fun getFollowers (

        @Path("profiloUtente")
        profiloUtente : String = Session.profileId,

        @Header("x-api-key")
        token: String = Session.token

    ) : Call<HomeWrapperResponse>

    @GET(value = "posts.php")
    fun getFollowerPost(

        @Header("x-api-key")
        token: String = Session.token

    ) : Call<HomeWrapperPostBean>

}
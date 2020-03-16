package com.example.util.retrofit

import com.example.bean.buissnes.*
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
        profiloUtente : String = Session.profileId

    ) : Call<HomeWrapperResponse>

    @GET(value = "posts.php")
    fun getFollowerPost() : Call<HomeWrapperPostBean>

    @GET(value= "profiles.php/{profiloUtente}")
    fun getProfileData (
        @Path("profiloUtente")
        profiloUtente : String = Session.profileId
    ) : Call<ProfileWrapperDataBean>

    @PUT(value= "profiles.php")
    fun updateProfileData (@Body profileUpdateRequest : ProfileModifyBean) : Call<ProfileUpdateResponse>

}
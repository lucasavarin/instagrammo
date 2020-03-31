package com.example.util.retrofit

import com.example.bean.buissnes.*
import com.example.bean.rest.request.CreatePostBeanRequest
import com.example.bean.rest.response.AuthResponse
import com.example.bean.rest.response.CreatePostBeanResponse
import com.example.bean.rest.response.NotificationResponseBean
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
    fun getFollowerPost(
//        @Query("from")  from: String,
//        @Query("elements")  elements: String
    ) : Call<HomeWrapperPostBean>

    @GET(value = "posts.php")
    fun getProceduralPosts(
        @Query("from")  from: Int,
        @Query("elements")  elements: Int
    ) : Call<List<HomePayloadPostBean>>

    @GET(value = "posts.php")
    fun getProceduralPost(
        @Query("from")  from: Int,
        @Query("elements")  elements: Int
    ) : Call<HomePayloadPostBean>


    @GET(value = "posts.php/{profiloUtente}")
    fun getPosts (
        @Path("profiloUtente")
        profiloUtente : String = Session.profileId
//        @Query("from")  from: String,
//        @Query("elements")  elements: String
    ) : Call<ProfileImgWrapper>

    @GET(value = "profiles.php/{profiloUtente}")
    fun getProfileData(
        @Path("profiloUtente")
        profiloUtente : String = Session.profileId
    ) : Call<ProfileWrapperDataBean>

    @PUT("profiles.php")
    fun updateProfileData(@Body updateBean : ProfileModifyBean) : Call<ProfileUpdateResponse>

    @POST(value = "posts.php")
    fun createPost(@Body createPostRequest : CreatePostBeanRequest) : Call<CreatePostBeanResponse>

    @GET("posts_number.php")
    fun getPostNumber() : Call<NotificationResponseBean>

}
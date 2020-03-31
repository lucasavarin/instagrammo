package com.example.instagrammo.retrofit

import com.example.instagrammo.beans.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterfacePicsum {

    @GET("v2/list")
    fun getAddList(
        @Query("limit")limit:Int?,
        @Query("page")page:Int?
    ):Call<List<AddPostResponseBeanREST>>

}
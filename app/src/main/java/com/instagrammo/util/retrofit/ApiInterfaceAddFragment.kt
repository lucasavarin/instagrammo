package com.instagrammo.util.retrofit

import com.instagrammo.bean.buissnes.AddPostResponseBean

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceAddFragment {

    @GET(value="/v2/list")
    fun getAddPost(@Query("page") page: Int = generateRandom(),
                   @Query("limit") limit: Int = 40)
                   : Call<List<AddPostResponseBean>>

}

fun generateRandom() : Int{

    val randomList = (1..30).shuffled().take(1)

    val randomNumber : Int = randomList[0]

    return randomNumber

}
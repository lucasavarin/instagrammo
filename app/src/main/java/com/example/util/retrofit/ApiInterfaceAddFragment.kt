package com.example.util.retrofit

import com.example.bean.buissnes.AddPostResponseBean
import com.example.util.utilities_project
import com.example.util.utilities_project.Utilities.generateRandomNumbers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceAddFragment {

    @GET(value="/v2/list")
    fun getAddPost(@Query("page") page: Int = generateRandomNumbers(1,30),
                   @Query("limit") limit: Int = 5)
                   : Call<List<AddPostResponseBean>>

}
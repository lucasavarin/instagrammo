package com.mst.instagrammo.api

import com.mst.instagrammo.model.AuthRequest
import com.mst.instagrammo.model.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST ("auth.php")
    fun doAuth(@Body authRequest : AuthRequest): Call<AuthResponse>
}
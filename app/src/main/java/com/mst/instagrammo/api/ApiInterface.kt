package com.mst.instagrammo.api

import com.mst.instagrammo.beans.AuthRequest
import com.mst.instagrammo.beans.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST ("auth.php")
    fun doAuth(@Body authRequest : AuthRequest): Call<AuthResponse>
}
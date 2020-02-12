package com.example.instagrammo.retrofit

import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(
        @Body authRequest: AuthRequest
    ): Call<AuthResponse>
}
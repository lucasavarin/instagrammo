package com.example.instagrammo

import model.AuthResponse
import model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface{

    @POST("auth.php")
    fun auth(@Body user : User): Call<AuthResponse>
}
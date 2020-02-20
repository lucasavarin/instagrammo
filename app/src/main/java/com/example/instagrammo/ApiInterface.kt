package com.example.instagrammo

import model.AuthResponse
import model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface{

    @POST("auth.php")
    fun auth(@Body user : User): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getStoriesList(@Path("profiloUtente") id:String) : Call<AuthResponse>
}
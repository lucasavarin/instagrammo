package com.example.login

import retrofit2.http.Body
import retrofit2.http.POST
import com.example.login.AuthRequest as AuthRequest1
import retrofit2.Call

interface APiInterface {

    @POST(value = "auth.php")
    fun getUser(@Body authRequest : AuthRequest1) : Call<AuthResponse>
}
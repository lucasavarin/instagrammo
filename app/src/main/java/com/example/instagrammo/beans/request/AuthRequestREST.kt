package com.example.instagrammo.beans.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthRequestREST(
    @Expose
    @SerializedName("username")
    val username: String,
    @Expose
    @SerializedName("password")
    val password: String
)
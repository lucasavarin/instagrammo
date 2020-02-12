package com.example.instagrammo.beans.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName(value="username") var username: String,
    @SerializedName(value="password") var password: String
    )
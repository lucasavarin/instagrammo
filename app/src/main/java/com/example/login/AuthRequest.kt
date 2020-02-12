package com.example.login

import com.google.gson.annotations.SerializedName

data class AuthRequest (
    @SerializedName(value = "username") val user : String,
    @SerializedName(value = "password") val pass : String
)

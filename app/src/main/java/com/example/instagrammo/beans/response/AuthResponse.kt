package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @Expose
    @SerializedName(value = "result")
    val result: Boolean,
    @Expose
    @SerializedName(value = "authToken")
    val authToken: String,
    @Expose
    @SerializedName(value = "IdProfile")
    val profileId: String
)
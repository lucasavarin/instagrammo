package com.example.instagrammo.beans.response

import com.google.gson.annotations.SerializedName

class AuthResponse(
    @SerializedName("result") val result: Boolean,
    @SerializedName("authToken") val authToken: String,
    @SerializedName("profileId") val profileId: Int
)
package com.mst.instagrammo.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @Expose
    @SerializedName("result") val result : String,
    @SerializedName("authToken") val authToken : String,
    @SerializedName("profileId") val profileId : String
)
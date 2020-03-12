package com.example.bean.rest.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse (

    @Expose()
    @SerializedName(value = "result") val result : Boolean,
    @Expose()
    @SerializedName(value = "authToken") val token : String,
    @Expose()
    @SerializedName(value = "profileId") val profileId : String

)


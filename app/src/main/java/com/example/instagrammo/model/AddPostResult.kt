package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddPostResult (
    @Expose()
    @SerializedName(value = "result") val result : Boolean
)
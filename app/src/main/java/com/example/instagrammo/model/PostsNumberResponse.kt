package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostsNumberResponse (
    @Expose
    @SerializedName("payload") val payload :String,
    @Expose
    @SerializedName("result") val result : Boolean
)
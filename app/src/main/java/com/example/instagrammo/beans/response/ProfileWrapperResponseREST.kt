package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileWrapperResponseREST(
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<ProfileResponseREST>,
    @Expose
    @SerializedName("count")
    val count: Int
)
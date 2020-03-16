package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileWrapperRest (
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<ProfileRest>,
    @Expose
    @SerializedName("count")
    val count: String
)
package com.example.instagrammo.beans.rest

import android.service.voice.AlwaysOnHotwordDetector
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostsWrapperResponseREST(
    @Expose
    @SerializedName("result")
    val result:Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<PostResponseREST>
)
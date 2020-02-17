package com.example.instagrammo.beans.response

import android.service.voice.AlwaysOnHotwordDetector

data class PostsWrapper(
    val result:Boolean,
    val payload: List<Post>
)
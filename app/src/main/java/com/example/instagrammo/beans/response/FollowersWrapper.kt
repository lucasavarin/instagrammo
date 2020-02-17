package com.example.instagrammo.beans.response

data class FollowersWrapper(
    val result: Boolean,
    val payload:List<Followers>
)
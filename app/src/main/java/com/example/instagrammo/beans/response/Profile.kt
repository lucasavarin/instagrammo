package com.example.instagrammo.beans.response

data class Profile(
    val profileId:String,
    val name:String,
    val description:String,
    val followersNumber:String,
    val postsNumber:String
)
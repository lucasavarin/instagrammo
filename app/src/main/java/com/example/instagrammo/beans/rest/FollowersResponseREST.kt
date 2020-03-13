package com.example.instagrammo.beans.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FollowersResponseREST (
    @Expose
    @SerializedName("id")
    val id:String,
    @Expose
    @SerializedName("name")
    val name:String,
    @Expose
    @SerializedName("description")
    val description:String,
    @Expose
    @SerializedName("picture")
    val picture:String
)
package com.example.instagrammo.beans.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponseREST(
    @Expose
    @SerializedName("profileId")
    val profileId:String,
    @Expose
    @SerializedName("name")
    val name:String,
    @Expose
    @SerializedName("description")
    val description:String,
    @Expose
    @SerializedName("followersNumber")
    val followersNumber:String,
    @Expose
    @SerializedName("postsNumber")
    val postsNumber:String,
    @Expose
    @SerializedName("picture")
    val picture: String
)
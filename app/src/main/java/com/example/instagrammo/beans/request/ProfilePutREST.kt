package com.example.instagrammo.beans.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePutREST(
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
    @SerializedName("picture")
    val picture:String
)
package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePostResponseREST(
    @Expose
    @SerializedName("postId")
    val postId:String,
    @Expose
    @SerializedName("title")
    val title:String,
    @Expose
    @SerializedName("picture")
    val picture:String,
    @Expose
    @SerializedName("uploadTime")
    val uploadTime:String

)
package com.example.bean.rest.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreatePostBeanRequest (

    @Expose()
    @SerializedName(value = "profileId") val profileId : String,
    @Expose()
    @SerializedName(value = "title") val title : String,
    @Expose()
    @SerializedName(value = "picture") val picture : String
)
package com.example.instagrammo.model.rest.response

import com.example.instagrammo.model.business.Post
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeWrapperPostBean(
    @Expose
    @SerializedName("result") val result : Boolean,
    @Expose
    @SerializedName("payload") val payload : List<Post>
)
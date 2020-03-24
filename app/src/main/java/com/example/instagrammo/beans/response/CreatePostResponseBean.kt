package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreatePostResponseBean(
    @Expose
    @SerializedName(value = "result") val result: Boolean
)
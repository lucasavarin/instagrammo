package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostPutResponseREST (
    @Expose
    @SerializedName("result")
    val result: Boolean
)
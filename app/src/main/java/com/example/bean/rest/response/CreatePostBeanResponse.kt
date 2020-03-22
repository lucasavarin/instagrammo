package com.example.bean.rest.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreatePostBeanResponse (
    @Expose()
    @SerializedName(value = "result") val result : Boolean
)
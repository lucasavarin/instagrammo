package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeFragmentResponse (
    @Expose
    @SerializedName(value = "result") val result: Boolean,
    @Expose
    @SerializedName(value = "payload") val payload: List<HomeUserResponseBean>
)
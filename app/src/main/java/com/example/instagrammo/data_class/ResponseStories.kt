package com.example.instagrammo.data_class

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseStories(
    @Expose
    @SerializedName("result") val result :String,
    @Expose
    @SerializedName("payload") val payload : Array<ProfiloStories>)
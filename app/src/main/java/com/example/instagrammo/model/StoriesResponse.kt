package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @Expose
    @SerializedName("result") val result :String,
    @Expose
    @SerializedName("payload") val payload : Array<Payload>) {

}

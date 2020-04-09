package com.example.instagrammo.model.business

import com.example.instagrammo.model.business.Payload
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @Expose
    @SerializedName("result") val result :String,
    @Expose
    @SerializedName("payload") val payload : Array<Payload>) {

}

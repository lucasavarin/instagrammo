package com.example.instagrammo.beans.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeUserResponseBean(
    @Expose
    @SerializedName(value = "id") val id : String,
    @Expose
    @SerializedName(value = "name") val name: String,
    @Expose
    @SerializedName(value = "description") val description: String,
    @Expose
    @SerializedName(value = "picture") val picture : String
)


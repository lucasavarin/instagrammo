package com.example.instagrammo.data_class

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfiloStories(
    @Expose
    @SerializedName(value = "id") val id : String,
    @Expose
    @SerializedName(value = "name") val name : String,
    @Expose
    @SerializedName(value = "description") val description : String,
    @Expose
    @SerializedName(value = "picture") val picture : String
)
package com.example.instagrammo.model.business

import com.google.gson.annotations.SerializedName

data class SaveDataBean (
        @SerializedName("profileId") val profileId : String,
        @SerializedName("name") val name : String,
        @SerializedName("description") val desc : String,
        @SerializedName("picture") val pic : String

)
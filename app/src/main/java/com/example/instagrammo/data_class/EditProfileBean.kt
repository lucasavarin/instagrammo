package com.example.instagrammo.data_class

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EditProfileBean(
    @Expose
    @SerializedName(value = "profileId") var profileId : String,
    @Expose
    @SerializedName(value = "name") var name : String,
    @Expose
    @SerializedName(value = "description") var description : String,
    @Expose
    @SerializedName(value = "picture") var picture : String
)
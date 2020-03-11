package model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("username") val nome : String,
                @SerializedName("password") val pass : String)

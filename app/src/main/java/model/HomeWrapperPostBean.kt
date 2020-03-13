package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeWrapperPostBean(
    @Expose
    @SerializedName("result") val result : Boolean,
    @Expose
    @SerializedName("payload") val payload : List<Post>
)
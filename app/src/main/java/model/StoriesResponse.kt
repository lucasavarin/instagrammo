package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @Expose
    @SerializedName("result") val result :String,
    @Expose
    @SerializedName("payload") val payload : Array<Payload>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StoriesResponse

        if (result != other.result) return false
        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = result.hashCode()
        result1 = 31 * result1 + payload.contentHashCode()
        return result1
    }
}

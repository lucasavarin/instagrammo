package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.annotations.Expose

data class HomeRecyclerViewVerticale (
    @Expose
    val result: Boolean,
    @Expose
    val payloadVerticale: Array<PayloadVerticale>
)

data class PayloadVerticale (
    @Expose
    val profileID: String,
    @Expose
    val postId: String,
    @Expose
    val title: String,
    @Expose
    val picture: String,
    @Expose
    val uploadTime: String,
    @Expose
    val profile: Profile
)

data class Profile(
    @Expose
    val profileId: String,
    @Expose
    val name: String,
    @Expose
    val description: String,
    @Expose
    val picture: String,
    @Expose
    val followersNumber: String,
    @Expose
    val postsNumber: String
)
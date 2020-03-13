package thushyanthan.scott.javalynx.instagrammo
import retrofit2.Call
import retrofit2.http.*
import thushyanthan.scott.javalynx.instagrammo.util.rest.*

interface ApiInterface {
        @POST(value = "auth.php")
        fun doAuth(
            @Body auth: DataRequest
        ):Call<AuthResponse>

        @GET("posts.php")
        fun requestPosts(): Call<PostsResponse>

        @GET("followers.php/{profiloUtente}")
        fun requestFollowers(@Path("profiloUtente") profiloUtente:String ): Call<FollowerResponse>

        @PUT("profiles.php")
        fun saveProfileEdits(@Body bodyProfilo: EditProfileBody): OnlyResultResponse
}